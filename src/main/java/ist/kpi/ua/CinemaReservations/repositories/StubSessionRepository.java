package ist.kpi.ua.CinemaReservations.repositories;

import org.springframework.stereotype.Repository;

import ist.kpi.ua.CinemaReservations.domain.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StubSessionRepository {
    
    private final List<Session> sessions = new ArrayList<>();
    
    public List<Session> findAll() {
        return sessions;
    }

    public Optional<Session> findById(Long id) {
        return sessions.stream().filter(session -> session.getId().equals(id)).findFirst();
    }

    public Session save(Session session) {
        sessions.add(session);
        return session;
    }

    public void deleteById(Long id) {
        sessions.removeIf(session -> session.getId().equals(id));
    }
}
