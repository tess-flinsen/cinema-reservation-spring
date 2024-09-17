package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Session session;

    @ManyToOne
    private Seat seat;

    private boolean isBooked; 

    private Double totalPrice;

    public Booking() {}

    public Booking(Session session, Seat seat, boolean isBooked) {
        this.session = session;
        this.seat = seat;
        this.isBooked = isBooked;
        this.totalPrice = calculateTotalPrice();
    }

    // загальна ціна бронювання обчислюється так: базова ціна сеансу * коефіцієнт місця
    // наприклад, центральні місця можуть мати вищу ціну, і, відповідно, коефіцієнт > 1 
    // за стандартом місця мають коефіцієнт 1, і загальна ціна = базовій ціні сеансу

    private Double calculateTotalPrice() {
        if (session != null && seat != null) {
            return session.getPrice() * (seat.getPriceModifier() != null ? seat.getPriceModifier() : 1);
        }
        return 0.0;  
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
}
