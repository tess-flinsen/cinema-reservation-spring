package ist.kpi.ua.CinemaReservations.service;

import org.springframework.stereotype.Service;

import ist.kpi.ua.CinemaReservations.domain.RegistrationRequest;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works!";
    }
}
