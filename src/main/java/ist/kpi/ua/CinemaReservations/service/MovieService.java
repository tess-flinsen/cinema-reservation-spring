package ist.kpi.ua.CinemaReservations.service;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
