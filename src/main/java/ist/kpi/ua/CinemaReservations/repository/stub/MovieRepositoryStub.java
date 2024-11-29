// package ist.kpi.ua.CinemaReservations.repository.stub;

// import ist.kpi.ua.CinemaReservations.domain.Movie;
// import ist.kpi.ua.CinemaReservations.domain.Session;
// import ist.kpi.ua.CinemaReservations.repository.MovieRepository;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// @Repository
// public class MovieRepositoryStub implements MovieRepository {

//     private List<Movie> movies = new ArrayList<>();

//     @Override
//     public List<Movie> findAll() {
//         Movie movie1 = new Movie("title1", null, 111);
//         Movie movie2 = new Movie("title2", "genre2", 222);
//         Movie movie3 = new Movie("title3", "genre3", 333);
//         movie1.setId(1L);
//         movie2.setId(2L);
//         movie3.setId(3L);

//         List<Session> sessionsForMovie1 = new ArrayList<>();
//         List<Session> sessionsForMovie2 = new ArrayList<>();
//         List<Session> sessionsForMovie3 = new ArrayList<>();

//         for (int i = 1; i <= 3; i++) {
//             Session session1 = new Session(movie1, LocalDateTime.of(2025, 1, i, 10, 0, 0), 150.0);
//             session1.setId(1L);
//             sessionsForMovie1.add(session1);

//             Session session2 = new Session(movie2, LocalDateTime.of(2025, 1, i, 11, 0, 0), 150.0);
//             session2.setId(2L);
//             sessionsForMovie2.add(session2);

//             Session session3 = new Session(movie2, LocalDateTime.of(2025, 1, i, 11, 30, 0), 150.0);
//             session3.setId(3L);
//             sessionsForMovie2.add(session3);

//             Session session4 = new Session(movie2, LocalDateTime.of(2025, 1, i, 12, 0, 0), 150.0);
//             session4.setId(4L);
//             sessionsForMovie2.add(session4);

//             Session session5 = new Session(movie3, LocalDateTime.of(2025, 1, i, 13, 0, 0), 150.0);
//             session5.setId(5L);
//             sessionsForMovie3.add(session5);
//         }

//         movie1.setSessions(sessionsForMovie1);
//         movie2.setSessions(sessionsForMovie2);
//         movie3.setSessions(sessionsForMovie3);

//         List<Movie> movies = new ArrayList<>();
//         movies.add(movie1);
//         movies.add(movie2);
//         movies.add(movie3);

//         return movies;
//     }

//     @Override
//     public Optional<Movie> findById(Long id) {
//         return movies.stream().filter(movie -> movie.getId().equals(id)).findFirst();
//     }

//     @Override
//     public Movie save(Movie movie) {
//         movies.removeIf(m -> m.getId().equals(movie.getId())); // заміна існуючого запису
//         movies.add(movie);
//         return movie;
//     }

//     @Override
//     public void deleteById(Long id) {
//         movies.removeIf(movie -> movie.getId().equals(id));
//     }
// }
