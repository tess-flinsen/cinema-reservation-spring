package ist.kpi.ua.CinemaReservations.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {

    private Long id;

    @NotEmpty
    private Long movieId;

    @NotEmpty
    private LocalDateTime startTime;

    @NotEmpty
    private Double price;
}

