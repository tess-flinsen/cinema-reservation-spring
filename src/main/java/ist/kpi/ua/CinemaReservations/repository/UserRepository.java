package ist.kpi.ua.CinemaReservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ist.kpi.ua.CinemaReservations.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
