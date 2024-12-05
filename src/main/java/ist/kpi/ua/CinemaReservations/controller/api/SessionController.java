package ist.kpi.ua.CinemaReservations.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ist.kpi.ua.CinemaReservations.domain.Session;
import ist.kpi.ua.CinemaReservations.dto.SessionDto;
import ist.kpi.ua.CinemaReservations.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final ModelMapper modelMapper;

    public SessionController(SessionService sessionService, ModelMapper modelMapper) {
        this.sessionService = sessionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all sessions", description = "Retrieves a list of all available sessions")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<SessionDto>> getAll() {
        List<Session> entities = sessionService.getAll();
        List<SessionDto> dtos = entities.stream()
                .map(entity -> modelMapper.map(entity, SessionDto.class))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get session by ID", description = "Retrieves a specific session by its unique identifier")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)))
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    public ResponseEntity<SessionDto> getById(@PathVariable Long id) {
        Optional<Session> entity = sessionService.getById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        SessionDto dto = modelMapper.map(entity.get(), SessionDto.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Create a new session", description = "Creates a new session based on the provided details in the request body")
    @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json"))
    public ResponseEntity<SessionDto> add(@RequestBody @Parameter(description = "Session details to be created") SessionDto sessionDto) {
        try {
            Session entity = modelMapper.map(sessionDto, Session.class);
            Session createdEntity = sessionService.save(entity);
            SessionDto createdDto = modelMapper.map(createdEntity, SessionDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing session", description = "Updates an existing session based on the provided details in the request body and path variable")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json"))
    public ResponseEntity<SessionDto> update(
            @PathVariable Long id,
            @RequestBody @Parameter(description = "Updated session details") SessionDto sessionDto
    ) {
        try {
            Session entity = modelMapper.map(sessionDto, Session.class);
            if (!id.equals(entity.getId())) {
                return ResponseEntity.badRequest().build();
            }
            Session updatedEntity = sessionService.save(entity);
            SessionDto updatedDto = modelMapper.map(updatedEntity, SessionDto.class);
            return ResponseEntity.ok(updatedDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a session", description = "Deletes a session by its unique identifier")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json"))
    public ResponseEntity<SessionDto> delete(@PathVariable Long id) {
        try {
            sessionService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}