package ist.kpi.ua.CinemaReservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ist.kpi.ua.CinemaReservations.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
