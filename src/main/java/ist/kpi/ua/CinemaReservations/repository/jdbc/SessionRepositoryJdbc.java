package ist.kpi.ua.CinemaReservations.repository.jdbc;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.repository.SessionRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class SessionRepositoryJdbc implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public SessionRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Session> sessionRowMapper = (rs, rowNum) -> {
        Session session = new Session();
        session.setId(rs.getLong("id"));
        session.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        session.setPrice(rs.getDouble("price"));
        Movie movie = new Movie();
        movie.setId(rs.getLong("movie_id"));
        session.setMovie(movie);
        return session;
    };

    @Override
    public List<Session> findAll() {
        String sql = "SELECT * FROM sessions";
        return jdbcTemplate.query(sql, sessionRowMapper);
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "SELECT * FROM sessions WHERE id = ?";
        List<Session> sessions = jdbcTemplate.query(sql, sessionRowMapper, id);
        return sessions.isEmpty() ? Optional.empty() : Optional.of(sessions.get(0));
    }

    @Override
    public Session save(Session session) {
        if (session.getId() == null) {
            String sql = "INSERT INTO sessions (movie_id, start_time, price) VALUES (?, ?, ?) RETURNING id";
            Long generatedId = jdbcTemplate.queryForObject(sql, Long.class,
                    session.getMovie().getId(),
                    session.getStartTime(),
                    session.getPrice());
            session.setId(generatedId);
        } else {
            String sql = "UPDATE sessions SET movie_id = ?, start_time = ?, price = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    session.getMovie().getId(),
                    session.getStartTime(),
                    session.getPrice(),
                    session.getId());
        }
        return session;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM sessions WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
