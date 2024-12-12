package ist.kpi.ua.CinemaReservations.repository.jpa;

import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.repository.SessionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepositoryJpa extends JpaRepository<Session, Long>, SessionRepository {
    List<Session> findAll();
    Optional<Session> findById(Long id);
    Session save(Session session);
    void deleteById(Long id);
}
