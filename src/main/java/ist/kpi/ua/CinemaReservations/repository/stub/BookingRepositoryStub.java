package ist.kpi.ua.CinemaReservations.repository.stub;

import ist.kpi.ua.CinemaReservations.domain.Booking;
import ist.kpi.ua.CinemaReservations.repository.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookingRepositoryStub implements BookingRepository {

    private List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookings.stream().filter(booking -> booking.getId().equals(id)).findFirst();
    }

    @Override
    public Booking save(Booking booking) {
        bookings.removeIf(b -> b.getId().equals(booking.getId())); 
        bookings.add(booking);
        return booking;
    }

    @Override
    public void deleteById(Long id) {
        bookings.removeIf(booking -> booking.getId().equals(id));
    }
}