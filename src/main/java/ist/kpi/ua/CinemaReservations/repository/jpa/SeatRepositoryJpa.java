package ist.kpi.ua.CinemaReservations.repository.jpa;

import ist.kpi.ua.CinemaReservations.domain.Seat;
import ist.kpi.ua.CinemaReservations.repository.SeatRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepositoryJpa extends JpaRepository<Seat, Long>, SeatRepository {
    List<Seat> findAll();
    Optional<Seat> findById(Long id);
    Seat save(Seat seat);
    void deleteById(Long id);
}
