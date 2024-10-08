package ist.kpi.ua.CinemaReservations.service;

import ist.kpi.ua.CinemaReservations.domain.Seat;
import ist.kpi.ua.CinemaReservations.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    /*public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }*/

    public List<Seat> getAll(){
        return seatRepository.findAll();
    }
}
