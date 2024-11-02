package ist.kpi.ua.CinemaReservations.controller.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ist.kpi.ua.CinemaReservations.domain.RegistrationRequest;
import ist.kpi.ua.CinemaReservations.service.RegistrationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrationController {
    private  RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
