package ist.kpi.ua.CinemaReservations.repository.stub;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepositoryStub implements MovieRepository {

    private List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movies.stream().filter(movie -> movie.getId().equals(id)).findFirst();
    }

    @Override
    public Movie save(Movie movie) {
        movies.removeIf(m -> m.getId().equals(movie.getId())); // заміна існуючого запису
        movies.add(movie);
        return movie;
    }

    @Override
    public void deleteById(Long id) {
        movies.removeIf(movie -> movie.getId().equals(id));
    }
}
