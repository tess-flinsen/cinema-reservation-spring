// package ist.kpi.ua.CinemaReservations.repository.stub;

// import ist.kpi.ua.CinemaReservations.domain.Movie;
// import ist.kpi.ua.CinemaReservations.domain.Session;
// import ist.kpi.ua.CinemaReservations.repository.SessionRepository;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// @Repository
// public class SessionRepositoryStub implements SessionRepository {

//     private List<Session> sessions = new ArrayList<>();

//     @Override
//     public List<Session> findAll() {
//         return new ArrayList<>(sessions);
//     }

//     @Override
//     public Optional<Session> findById(Long id) {
//         // return sessions.stream().filter(session -> session.getId().equals(id)).findFirst();
//         return Optional.of(new Session(new Movie("Movie title", "Movie genre", 123),
//                 LocalDateTime.of(2025, 01, 01, 15, 30, 0),
//                 100.0));
//     }

//     @Override
//     public Session save(Session session) {
//         sessions.removeIf(s -> s.getId().equals(session.getId())); 
//         sessions.add(session);
//         return session;
//     }

//     @Override
//     public void deleteById(Long id) {
//         sessions.removeIf(session -> session.getId().equals(id));
//     }
// }