package ist.kpi.ua.CinemaReservations.repository.jdbc;

import ist.kpi.ua.CinemaReservations.domain.Booking;
import ist.kpi.ua.CinemaReservations.domain.Seat;
import ist.kpi.ua.CinemaReservations.repository.BookingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class BookingRepositoryJdbc implements BookingRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookingRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Booking> bookingRowMapper = (rs, rowNum) -> {
        Booking booking = new Booking();
        booking.setId(rs.getLong("id"));
        booking.setBooked(rs.getBoolean("is_booked"));
        booking.setTotalPrice(rs.getDouble("total_price"));
        return booking;
    };

    @Override
    public List<Booking> findAll() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, bookingRowMapper);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        List<Booking> bookings = jdbcTemplate.query(sql, bookingRowMapper, id);
        return bookings.isEmpty() ? Optional.empty() : Optional.of(bookings.get(0));
    }

    @Override
    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            String insertSql = "INSERT INTO bookings (session_id, is_booked, total_price) VALUES (?, ?, ?) RETURNING id";
            Long generatedId = jdbcTemplate.queryForObject(insertSql, Long.class, booking.getSession().getId(), booking.isBooked(), booking.getTotalPrice());
            booking.setId(generatedId);
        } else {
            String updateSql = "UPDATE bookings SET session_id = ?, is_booked = ?, total_price = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, booking.getSession().getId(), booking.isBooked(), booking.getTotalPrice(), booking.getId());
        }

        String deleteSeatsSql = "DELETE FROM booking_seat WHERE booking_id = ?";
        jdbcTemplate.update(deleteSeatsSql, booking.getId());

        String insertSeatsSql = "INSERT INTO booking_seat (booking_id, seat_id) VALUES (?, ?)";
        for (Seat seat : booking.getSeats()) {
            jdbcTemplate.update(insertSeatsSql, booking.getId(), seat.getId());
        }

        return booking;
    }


    @Override
    public void deleteById(Long id) {
        String deleteSeatsSql = "DELETE FROM seats WHERE booking_id = ?";
        jdbcTemplate.update(deleteSeatsSql, id);
        String deleteBookingSql = "DELETE FROM bookings WHERE id = ?";
        jdbcTemplate.update(deleteBookingSql, id);
    }
}
