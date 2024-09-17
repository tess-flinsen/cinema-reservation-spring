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

    @OneToMany(mappedBy = "seat")
    private List<Booking> bookings;
    
    public Seat() {}

    public Seat(Integer rowNumber, Integer seatNumber, boolean isAvailable) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
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

}
