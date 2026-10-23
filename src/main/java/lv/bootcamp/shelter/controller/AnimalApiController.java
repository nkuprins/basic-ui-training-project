package lv.bootcamp.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lv.bootcamp.shelter.dto.AnimalCreateRequest;
import lv.bootcamp.shelter.dto.AnimalResponse;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for shelter animal endpoints.
 * Returns JSON — does not render HTML pages.
 */
@Tag(name = "Animals", description = "Shelter animals and adoptions")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animals")
public class AnimalApiController {

    private final AnimalService animalService;

    @Operation(summary = "List all animals", description = "Returns every animal in the shelter, sorted by ID.")
    @GetMapping
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }

    @Operation(summary = "Get an animal by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findById(@PathVariable Long id) {
        return animalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lists adopted animals.
     */
    @Operation(summary = "List adopted animals")
    @GetMapping("/adopted")
    public List<AnimalResponse> findAdopted() {
        return animalService.findAdopted();
    }

    /**
     * Creates a new animal.
     */
    @Operation(summary = "Create an animal", description = "New animals start as AVAILABLE. If no image is given, a fallback is chosen by type.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse create(@RequestBody @Valid AnimalCreateRequest request) {
        return animalService.create(request);
    }

    /**
     * Adopts an animal
     */
    @Operation(summary = "Adopt an animal", description = "Marks the animal as ADOPTED and records today's date.")
    @PostMapping("/{id}/adopt")
    public ResponseEntity<AnimalResponse> adopt(@PathVariable Long id) {
        return animalService.adopt(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleAlreadyAdopted(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
