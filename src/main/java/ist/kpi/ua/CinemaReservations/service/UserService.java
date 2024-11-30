package ist.kpi.ua.CinemaReservations.service;

import java.util.List;

import ist.kpi.ua.CinemaReservations.domain.User;
import ist.kpi.ua.CinemaReservations.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
