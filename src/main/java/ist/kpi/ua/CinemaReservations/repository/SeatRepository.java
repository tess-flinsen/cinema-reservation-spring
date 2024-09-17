package ist.kpi.ua.CinemaReservations.repository;

import ist.kpi.ua.CinemaReservations.domain.Seat;
import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    List<Seat> findAll();
    Optional<Seat> findById(Long id);
    Seat save(Seat seat);
    void deleteById(Long id);
}