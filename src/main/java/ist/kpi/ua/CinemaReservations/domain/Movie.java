package ist.kpi.ua.CinemaReservations.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private Integer duration;

    @OneToMany(mappedBy = "movie")
    private List<Session> sessions;

    public Movie() {};

    public Movie(String title, String genre, Integer duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setSessions(List<Session> sessions) {this.sessions = sessions;}
    public List<Session> getSessions() { return sessions; }




}
