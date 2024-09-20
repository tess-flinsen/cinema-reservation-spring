package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Session session;

    @ManyToMany(mappedBy = "bookings")
    private List<Seat> seats;

    private boolean isBooked; 

    private Double totalPrice;

    public Booking() {}

    public Booking(Session session, List<Seat> seats, boolean isBooked) {
        this.session = session;
        this.seats = seats;
        this.isBooked = isBooked;
        this.totalPrice = calculateTotalPrice();
    }

    // загальна ціна бронювання обчислюється так: базова ціна сеансу * коефіцієнт місця
    // наприклад, центральні місця можуть мати вищу ціну, і, відповідно, коефіцієнт > 1 
    // за стандартом місця мають коефіцієнт 1, і загальна ціна = базовій ціні сеансу

    private Double calculateTotalPrice() {
        /*if (session != null && seat != null) {
            return session.getPrice() * (seat.getPriceModifier() != null ? seat.getPriceModifier() : 1);
        }
        return 0.0;  */

        if (session == null || seats.stream().anyMatch(Objects::isNull)){
            return 0.0;
        }
        return seats.stream()
                    .map(seat ->
                            session.getPrice() *
                                   (seat.getPriceModifier() != null ?
                                    seat.getPriceModifier() : 1))
                    .reduce(0.0, Double::sum);
    }
    
    public void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
}
