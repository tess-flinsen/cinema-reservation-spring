package ist.kpi.ua.CinemaReservations.repository.jpa;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepositoryJpa extends JpaRepository<Movie, Long>, MovieRepository {
    List<Movie> findAll();
    Page<Movie> findAll(Pageable pageable);
    Optional<Movie> findById(Long id);
    Movie save(Movie movie);
    void deleteById(Long id);

    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    Page<Movie> findByGenreContaining(String genre, Pageable pageable);
    Page<Movie> findByTitleContainingAndGenreContaining(String title, String genre, Pageable pageable);
}
