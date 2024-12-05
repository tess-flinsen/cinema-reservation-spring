package ist.kpi.ua.CinemaReservations.controller.api;

import ist.kpi.ua.CinemaReservations.domain.Movie;
import ist.kpi.ua.CinemaReservations.dto.MovieDto;
import ist.kpi.ua.CinemaReservations.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all movies",
            description = "Retrieves a page of movies based on optional filter criteria and pagination parameters.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    public ResponseEntity<Page<MovieDto>> getAll(
            @RequestParam(required = false)
            @Parameter(description = "Filter movies by title (optional)")
            String title,
            @RequestParam(required = false)
            @Parameter(description = "Filter movies by genre (optional)")
            String genre,
            @RequestParam(defaultValue = "0")
            @Parameter(description = "Page number (default 0)")
            int page,
            @RequestParam(defaultValue = "5")
            @Parameter(description = "Page size (default 5)")
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> entities = movieService.getAllFiltered(title, genre, pageable);
        Page<MovieDto> dtos = entities.map(entity -> modelMapper.map(entity, MovieDto.class));
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a movie by ID", description = "Retrieves a single movie by its unique identifier.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)))
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
        Optional<Movie> entity = movieService.getById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        MovieDto dto = modelMapper.map(entity.get(), MovieDto.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Create a new movie", description = "Creates a new movie based on the provided MovieDto object in the request body.")
    @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json"))
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {
        try {
            Movie entity = modelMapper.map(movieDto, Movie.class);
            Movie createdEntity = movieService.save(entity);
            MovieDto createdDto = modelMapper.map(createdEntity, MovieDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie", description = "Updates an existing movie based on the provided MovieDto object in the request body. The ID in the path must match the ID in the request body.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json"))
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        try {
            Movie entity = modelMapper.map(movieDto, Movie.class);
            if (!id.equals(entity.getId())) {
                return ResponseEntity.badRequest().build();
            }
            Movie updatedEntity = movieService.save(entity);
            MovieDto updatedDto = modelMapper.map(updatedEntity, MovieDto.class);
            return ResponseEntity.ok(updatedDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie", description = "Deletes an existing movie by its ID.")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json"))
    public ResponseEntity<MovieDto> delete(@PathVariable Long id) {
        try {
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
       catch (Exception e){
            return ResponseEntity.badRequest().build();
       }
    }
}
