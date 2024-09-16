package ist.kpi.ua.CinemaReservations.repositories;

import org.springframework.stereotype.Repository;

import ist.kpi.ua.CinemaReservations.domain.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StubSeatRepository {

    private final List<Seat> seats = new ArrayList<>();
    
    public List<Seat> findAll() {
        return seats;
    }

    public Optional<Seat> findById(Long id) {
        return seats.stream().filter(seat -> seat.getId().equals(id)).findFirst();
    }

    public Seat save(Seat seat) {
        seats.add(seat);
        return seat;
    }

    public void deleteById(Long id) {
        seats.removeIf(seat -> seat.getId().equals(id));
    }
}

