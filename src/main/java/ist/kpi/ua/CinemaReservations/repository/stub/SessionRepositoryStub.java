package ist.kpi.ua.CinemaReservations.repository.stub;

import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.repository.SessionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionRepositoryStub implements SessionRepository {

    private List<Session> sessions = new ArrayList<>();

    @Override
    public List<Session> findAll() {
        return new ArrayList<>(sessions);
    }

    @Override
    public Optional<Session> findById(Long id) {
        return sessions.stream().filter(session -> session.getId().equals(id)).findFirst();
    }

    @Override
    public Session save(Session session) {
        sessions.removeIf(s -> s.getId().equals(session.getId())); 
        sessions.add(session);
        return session;
    }

    @Override
    public void deleteById(Long id) {
        sessions.removeIf(session -> session.getId().equals(id));
    }
}