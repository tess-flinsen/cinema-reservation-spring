package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Session session;

    @ManyToMany
    @JoinTable(
        name = "booking_seat",  // Name of the join table
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;

    @Column(nullable = false)
    private boolean isBooked; 

    @Column(nullable = false)
    private Double totalPrice;

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

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
}
