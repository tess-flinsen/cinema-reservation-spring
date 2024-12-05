package ist.kpi.ua.CinemaReservations.service;

import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /*public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }*/

    public Optional<Session> getById(Long id) {
        return sessionRepository.findById(id);
    }

    public List<Session> getAll() { return sessionRepository.findAll(); }

    public Session save(Session session) { return sessionRepository.save(session); }

    public void deleteById(Long id) { sessionRepository.deleteById(id); }
}
