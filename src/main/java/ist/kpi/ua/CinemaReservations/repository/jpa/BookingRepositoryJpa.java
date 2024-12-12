package ist.kpi.ua.CinemaReservations.repository.jpa;

import ist.kpi.ua.CinemaReservations.domain.Booking;

import java.util.List;
import java.util.Optional;

import ist.kpi.ua.CinemaReservations.repository.BookingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepositoryJpa extends JpaRepository<Booking, Long>, BookingRepository {
    List<Booking> findAll();
    Optional<Booking> findById(Long id);
    Booking save(Booking booking);
    void deleteById(Long id);
}