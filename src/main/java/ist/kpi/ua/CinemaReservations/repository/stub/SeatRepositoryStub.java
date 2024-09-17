package ist.kpi.ua.CinemaReservations.repository.stub;

import ist.kpi.ua.CinemaReservations.domain.Seat;
import ist.kpi.ua.CinemaReservations.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatRepositoryStub implements SeatRepository {

    private List<Seat> seats = new ArrayList<>();

    @Override
    public List<Seat> findAll() {
        return new ArrayList<>(seats);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seats.stream().filter(seat -> seat.getId().equals(id)).findFirst();
    }

    @Override
    public Seat save(Seat seat) {
        seats.removeIf(s -> s.getId().equals(seat.getId()));
        seats.add(seat);
        return seat;
    }

    @Override
    public void deleteById(Long id) {
        seats.removeIf(seat -> seat.getId().equals(id));
    }
}