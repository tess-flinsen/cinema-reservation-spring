package ist.kpi.ua.CinemaReservations.controller.web;

import ist.kpi.ua.CinemaReservations.domain.Seat;
import ist.kpi.ua.CinemaReservations.service.SeatService;
import ist.kpi.ua.CinemaReservations.service.SessionService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class SeatSelectionController {

    private final SessionService sessionService;
    private final SeatService seatService;


    public SeatSelectionController(SessionService sessionService,
                                   SeatService seatService) {
        this.sessionService = sessionService;
        this.seatService = seatService;
    }

    @GetMapping("/seats/{sessionId}")
    public String getAllSeatsBySessionId(@PathVariable Long sessionId, Model model, Authentication authentication){
        List<Seat> seats = seatService.getAll();
        seats.forEach(seat -> {
                  if (Stream.ofNullable(seat.getBookings())
                          .flatMap(Collection::stream)
                          .anyMatch(b -> b.getSession().getId() == sessionId)){
                      seat.setAvailable(false);
                  }
              });

        Map<Integer, List<Seat>> groupedSeats = seats.stream()
                        .collect(Collectors.groupingBy(Seat::getRowNumber));

        model.addAttribute("groupedSeats", groupedSeats);
        model.addAttribute("movieSession", sessionService.getById(sessionId).orElse(null));
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        } else {
            model.addAttribute("username", "Guest");
        }   
        return "seats";
    }

}
