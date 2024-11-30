package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String title;
    private String genre;
    @Column(nullable = false)
    private Integer duration;

    @OneToMany(
        mappedBy = "movie",
        cascade = CascadeType.ALL, // Cascade persist, merge, etc.
        //orphanRemoval = true,      // Remove sessions when the movie is deleted
        fetch = FetchType.LAZY     // Load sessions only when accessed
    )
    private List<Session> sessions = new ArrayList<>();

    public Movie(String title, String genre, Integer duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public void setSessions(List<Session> sessions) {this.sessions = sessions;}
    public List<Session> getSessions() { return sessions; }
}
