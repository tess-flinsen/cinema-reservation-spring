package ist.kpi.ua.CinemaReservations.controller.web;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.service.MovieService;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final MovieService movieService;

    public AdminController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/create")
    public String adminCreate() {
        return "create";  // повертає HTML сторінку для створення сеансу
    }

    @GetMapping("/update")
    public String adminUpdate() {
        return "update";  // повертає HTML сторінку для редагування сеансу
    }

    @GetMapping("/delete")
    public String adminDelete() {
        return "delete";  // повертає HTML сторінку для видалення сеансу
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Додаємо код для отримання даних про фільми та сеанси
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
        return "dashboard";  // повертає HTML сторінку для адміністративної панелі
    }
}
