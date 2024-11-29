package ist.kpi.ua.CinemaReservations.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ist.kpi.ua.CinemaReservations.repository.*;
import ist.kpi.ua.CinemaReservations.domain.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
        loadMoviesAndSessions();
        loadSeatsAndBookings();
    }
    
    private void loadMoviesAndSessions() {
        Movie movie1 = new Movie("Title 1", null, 111);
        Movie movie2 = new Movie("Title 2", "Genre 2", 222);
        Movie movie3 = new Movie("Title 3", "Genre 3", 333);

        movie1 = movieRepository.save(movie1);
        movie2 = movieRepository.save(movie2);
        movie3 = movieRepository.save(movie3);

        List<Session> sessionsForMovie1 = new ArrayList<>();
        List<Session> sessionsForMovie2 = new ArrayList<>();
        List<Session> sessionsForMovie3 = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            sessionsForMovie1.add(sessionRepository.save(
                new Session(movie1, LocalDateTime.of(2025, 1, i, 10, 0, 0), 150.0)
            ));

            sessionsForMovie2.add(sessionRepository.save(
                new Session(movie2, LocalDateTime.of(2025, 1, i, 11, 0, 0), 150.0)
            ));

            sessionsForMovie2.add(sessionRepository.save(
                new Session(movie2, LocalDateTime.of(2025, 1, i, 11, 30, 0), 150.0)
            ));

            sessionsForMovie2.add(sessionRepository.save(
                new Session(movie2, LocalDateTime.of(2025, 1, i, 12, 0, 0), 150.0)
            ));

            sessionsForMovie3.add(sessionRepository.save(
                new Session(movie3, LocalDateTime.of(2025, 1, i, 13, 0, 0), 150.0)
            ));
        }
    }

    private void loadSeatsAndBookings() {
        List<Seat> seats = new ArrayList<>();
        int rowNum = 5;
        int colNum = 8;

        for (int row = 1; row <= rowNum; row++) {
            for (int col = 1; col <= colNum; col++) {
                Seat seat = new Seat(row, col, true, 1.0);
                seat = seatRepository.save(seat);

                // Simulate booking for some seats
                if (row * col % 2 == 1) {
                    Booking booking = new Booking();
                    Session session = sessionRepository.findById((long) ((row - 1) * colNum + col))
                            .orElse(null);

                    if (session != null) {
                        booking.setSession(session);
                        booking.setSeats(List.of(seat));
                        booking.setBooked(true);
                        booking.updateTotalPrice();
                        booking = bookingRepository.save(booking);

                        seat.addBooking(booking);
                        seatRepository.save(seat);
                    }
                }

                seats.add(seat);
            }
        }
    }

}