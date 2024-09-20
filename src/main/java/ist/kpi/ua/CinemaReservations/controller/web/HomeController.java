package ist.kpi.ua.CinemaReservations.controller.web;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.service.MovieService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String index(Model model){

        Map<Movie, Map<String, List<Session>>> moviesWithGroupedSessions = movieService.getAll().stream()
                .collect(Collectors.toMap(
                        movie -> movie,
                        movie -> movie.getSessions().stream()
                                .collect(Collectors.groupingBy(
                                        session -> session.getStartTime()
                                                .toLocalDate()
                                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd, E")),
                                        LinkedHashMap::new,
                                        Collectors.toList()
                                )),
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        model.addAttribute("moviesWithGroupedSessions", moviesWithGroupedSessions);
        return "index";
    }
}
