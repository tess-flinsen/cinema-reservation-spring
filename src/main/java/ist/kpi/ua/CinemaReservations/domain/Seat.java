package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer rowNumber;
    private Integer seatNumber;
    private boolean isAvailable;

    private Double priceModifier = 1.0;  // за замовчуванням коефіцієнт ціни за певне місце = 1

    @OneToMany(mappedBy = "seat")
    private List<Booking> bookings;
    
    public Seat() {}

    public Seat(Integer rowNumber, Integer seatNumber, boolean isAvailable) { // використовується коефіцієнт ціни за замовчуванням
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    public Seat(Integer rowNumber, Integer seatNumber, boolean isAvailable, Double priceModifier) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        setPriceModifier(priceModifier);  // використовуємо сеттер для перевірки валідності коефіцієнту
    }

    public List<Booking> getBookings() { 
        return bookings; 
    }
    public void setBookings(List<Booking> bookings) { 
        this.bookings = bookings; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Double getPriceModifier() { 
        return priceModifier; 
    }

    // коефіцієнт ціни має бути в межах від 1 до 3 (ціна за окреме місце не може бути вищою, ніж в 3 рази від базової)
    public void setPriceModifier(Double priceModifier) {
        if (priceModifier < 1.0) {
            this.priceModifier = 1.0;
        } else if (priceModifier > 3.0) {
            this.priceModifier = 3.0;
        } else {
            this.priceModifier = priceModifier;
        }
    }

}
