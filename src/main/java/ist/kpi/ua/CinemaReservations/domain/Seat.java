package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "seats")
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer rowNumber;
    private Integer seatNumber;

    @Column(nullable = false)
    private boolean isAvailable;

    private Double priceModifier = 1.0;  // за замовчуванням коефіцієнт ціни за певне місце = 1

    @ManyToMany(mappedBy = "seats")
    private List<Booking> bookings;


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

    public void addBooking(Booking booking) {
        if (this.bookings == null) {
            this.bookings = new ArrayList<Booking>(List.of(booking));
        }
        else {
            this.bookings.add(booking);
        }
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
