package ist.kpi.ua.CinemaReservations.repository;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    Movie save(Movie movie);
    void deleteById(Long id);

    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    Page<Movie> findByGenreContaining(String genre, Pageable pageable);
    Page<Movie> findByTitleContainingAndGenreContaining(String title, String genre, Pageable pageable);
}