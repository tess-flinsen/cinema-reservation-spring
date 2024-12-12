package ist.kpi.ua.CinemaReservations.repository.jdbc;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.repository.MovieRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Movie> movieRowMapper = (rs, rowNum) -> {
        Movie movie = new Movie();
        movie.setId(rs.getLong("id"));
        movie.setTitle(rs.getString("title"));
        movie.setGenre(rs.getString("genre"));
        movie.setDuration(rs.getInt("duration"));
        return movie;
    };

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
    public List<Movie> findAll() {
        String sql = "SELECT * FROM movies";
        List<Movie> movies = jdbcTemplate.query(sql, movieRowMapper);

        for (Movie movie : movies) {
            String sessionSql = "SELECT * FROM sessions WHERE movie_id = ?";
            List<Session> sessions = jdbcTemplate.query(sessionSql, sessionRowMapper, movie.getId());
            movie.setSessions(sessions);
        }
        return movies;
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        String countSql = "SELECT COUNT(*) FROM movies";
        String querySql = "SELECT * FROM movies LIMIT ? OFFSET ?";
        int total = jdbcTemplate.queryForObject(countSql, Integer.class);
        List<Movie> movies = jdbcTemplate.query(querySql, movieRowMapper, pageable.getPageSize(), pageable.getOffset());
        return new PageImpl<>(movies, pageable, total);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        List<Movie> movies = jdbcTemplate.query(sql, movieRowMapper, id);
        return movies.isEmpty() ? Optional.empty() : Optional.of(movies.get(0));
    }

    @Override
    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            String sql = "INSERT INTO movies (title, genre, duration) VALUES (?, ?, ?) RETURNING id";
            Long id = jdbcTemplate.queryForObject(sql, Long.class, movie.getTitle(), movie.getGenre(), movie.getDuration());
            movie.setId(id);
        } else {
            String sql = "UPDATE movies SET title = ?, genre = ?, duration = ? WHERE id = ?";
            jdbcTemplate.update(sql, movie.getTitle(), movie.getGenre(), movie.getDuration(), movie.getId());
        }
        return movie;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Page<Movie> findByTitleContaining(String title, Pageable pageable) {
        String countSql = "SELECT COUNT(*) FROM movies WHERE title LIKE ?";
        String querySql = "SELECT * FROM movies WHERE title LIKE ? LIMIT ? OFFSET ?";

        String likeTitle = "%" + title + "%";
        int total = jdbcTemplate.queryForObject(countSql, Integer.class, likeTitle);

        List<Movie> movies = jdbcTemplate.query(querySql, movieRowMapper, likeTitle, pageable.getPageSize(), pageable.getOffset());
        return new PageImpl<>(movies, pageable, total);
    }

    @Override
    public Page<Movie> findByGenreContaining(String genre, Pageable pageable) {
        String countSql = "SELECT COUNT(*) FROM movies WHERE genre LIKE ?";
        String querySql = "SELECT * FROM movies WHERE genre LIKE ? LIMIT ? OFFSET ?";

        String likeGenre = "%" + genre + "%";
        int total = jdbcTemplate.queryForObject(countSql, Integer.class, likeGenre);

        List<Movie> movies = jdbcTemplate.query(querySql, movieRowMapper, likeGenre, pageable.getPageSize(), pageable.getOffset());
        return new PageImpl<>(movies, pageable, total);
    }

    @Override
    public Page<Movie> findByTitleContainingAndGenreContaining(String title, String genre, Pageable pageable) {
        String countSql = "SELECT COUNT(*) FROM movies WHERE title LIKE ? AND genre LIKE ?";
        String querySql = "SELECT * FROM movies WHERE title LIKE ? AND genre LIKE ? LIMIT ? OFFSET ?";

        String likeTitle = "%" + title + "%";
        String likeGenre = "%" + genre + "%";
        int total = jdbcTemplate.queryForObject(countSql, Integer.class, likeTitle, likeGenre);

        List<Movie> movies = jdbcTemplate.query(querySql, movieRowMapper, likeTitle, likeGenre, pageable.getPageSize(), pageable.getOffset());
        return new PageImpl<>(movies, pageable, total);
    }
}