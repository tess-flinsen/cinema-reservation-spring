package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

import java.time.LocalDateTime;

@Entity
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Movie movie; 

    private LocalDateTime startTime;
    private Double price;

    @OneToMany(mappedBy = "session")
    private List<Booking> bookings;
    
    public Session() {}

    public Session(Movie movie, LocalDateTime startTime, Double price) {
        this.movie = movie;
        this.startTime = startTime;
        this.price = price;
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

    public Movie getMovie() { 
        return movie; 
    }
    public void setMovie(Movie movie) { 
        this.movie = movie; 
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
