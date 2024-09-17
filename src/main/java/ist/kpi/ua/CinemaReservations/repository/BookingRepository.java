package ist.kpi.ua.CinemaReservations.repository;

import ist.kpi.ua.CinemaReservations.domain.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<Booking> findAll();
    Optional<Booking> findById(Long id);
    Booking save(Booking booking);
    void deleteById(Long id);
}