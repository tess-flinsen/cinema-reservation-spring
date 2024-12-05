package ist.kpi.ua.CinemaReservations.service;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /*public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }*/

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public Page<Movie> getAllFiltered(String title, String genre, Pageable pageable) {
        if (title != null && genre != null) {
            return movieRepository.findByTitleContainingAndGenreContaining(title, genre, pageable);
        } else if (title != null) {
            return movieRepository.findByTitleContaining(title, pageable);
        } else if (genre != null) {
            return movieRepository.findByGenreContaining(genre, pageable);
        } else {
            return movieRepository.findAll(pageable);
        }
    }

    public Optional<Movie> getById(Long id){ return movieRepository.findById(id); }

    public Movie save(Movie movie) { return movieRepository.save(movie); }

    public void deleteById(Long id) { movieRepository.deleteById(id); }
}
