package ist.kpi.ua.CinemaReservations.repository;

import ist.kpi.ua.CinemaReservations.domain.Session;
import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    List<Session> findAll();
    Optional<Session> findById(Long id);
    Session save(Session session);
    void deleteById(Long id);
}