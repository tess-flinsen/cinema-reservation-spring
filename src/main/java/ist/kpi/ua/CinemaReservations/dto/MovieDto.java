package ist.kpi.ua.CinemaReservations.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String genre;

    @NotEmpty
    private Integer duration;
}
