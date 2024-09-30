package ist.kpi.ua.CinemaReservations.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";  // повертає HTML сторінку для користувача
    }

    // Інші сторінки для користувача
}

