package ist.kpi.ua.CinemaReservations.repository.jdbc;

import ist.kpi.ua.CinemaReservations.domain.Seat;
import ist.kpi.ua.CinemaReservations.repository.SeatRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class SeatRepositoryJdbc implements SeatRepository {

    private final JdbcTemplate jdbcTemplate;

    public SeatRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Seat> seatRowMapper = (rs, rowNum) -> {
        Seat seat = new Seat();
        seat.setId(rs.getLong("id"));
        seat.setRowNumber(rs.getInt("row_number"));
        seat.setSeatNumber(rs.getInt("seat_number"));
        seat.setAvailable(rs.getBoolean("is_available"));
        seat.setPriceModifier(rs.getDouble("price_modifier"));
        return seat;
    };

    @Override
    public List<Seat> findAll() {
        String sql = "SELECT * FROM seats";
        return jdbcTemplate.query(sql, seatRowMapper);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        String sql = "SELECT * FROM seats WHERE id = ?";
        try {
            Seat seat = jdbcTemplate.queryForObject(sql, seatRowMapper, id);
            return Optional.ofNullable(seat);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Seat save(Seat seat) {
        if (seat.getId() == null) {
            String sql = "INSERT INTO seats (row_number, seat_number, is_available, price_modifier) VALUES (?, ?, ?, ?) RETURNING id";
            Long generatedId = jdbcTemplate.queryForObject(sql, Long.class,
                    seat.getRowNumber(),
                    seat.getSeatNumber(),
                    seat.isAvailable(),
                    seat.getPriceModifier()
            );
            seat.setId(generatedId);
        } else {
            String sql = "UPDATE seats SET row_number = ?, seat_number = ?, is_available = ?, price_modifier = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    seat.getRowNumber(),
                    seat.getSeatNumber(),
                    seat.isAvailable(),
                    seat.getPriceModifier(),
                    seat.getId()
            );
        }
        return seat;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM seats WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
