// package ist.kpi.ua.CinemaReservations.repository.stub;

// import ist.kpi.ua.CinemaReservations.domain.Booking;
// import ist.kpi.ua.CinemaReservations.domain.Seat;
// import ist.kpi.ua.CinemaReservations.domain.Session;
// import ist.kpi.ua.CinemaReservations.repository.SeatRepository;
// import org.springframework.stereotype.Repository;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// @Repository
// public class SeatRepositoryStub implements SeatRepository {

//     private List<Seat> seats = new ArrayList<>();

//     @Override
//     public List<Seat> findAll() {

//         // return new ArrayList<>(seats);
//         List<Seat> seats = new ArrayList<>();

//         int rowNum = 5;
//         int colNum = 8;
//         for (int row = 1; row <= rowNum; row++) {
//             for (int col = 1; col <= colNum; col++) {
//                 Seat seat = new Seat();
//                 seat.setId((long) (row-1)*colNum + col);
//                 seat.setRowNumber(row);
//                 seat.setSeatNumber(col);
//                 seat.setAvailable(true);
//                 seat.setPriceModifier(1.0);

//                 if (row * col % 2 == 1) {
//                     Booking booking = new Booking();
//                     booking.setId((long) (row-1)*colNum + col);
//                     booking.setSeats(List.of(seat));

//                     Session session = new Session();
//                     session.setId(booking.getId());
//                     booking.setSession(session);

//                     seat.addBooking(booking);
//                 }

//                 seats.add(seat);
//             }
//         }

//         return seats;
//     }

//     @Override
//     public Optional<Seat> findById(Long id) {
//         return seats.stream().filter(seat -> seat.getId().equals(id)).findFirst();
//     }

//     @Override
//     public Seat save(Seat seat) {
//         seats.removeIf(s -> s.getId().equals(seat.getId()));
//         seats.add(seat);
//         return seat;
//     }

//     @Override
//     public void deleteById(Long id) {
//         seats.removeIf(seat -> seat.getId().equals(id));
//     }
// }