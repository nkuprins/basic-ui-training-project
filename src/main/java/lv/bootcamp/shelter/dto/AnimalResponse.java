package lv.bootcamp.shelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lv.bootcamp.shelter.model.AnimalStatus;
import lv.bootcamp.shelter.model.AnimalType;

/**
 * Response body for a single animal returned by the API.
 * {@code adoptionNote} is only populated for ADMIN callers (see AnimalService#toResponse) —
 * everyone else just sees the plain {@code status}.
 */
public record AnimalResponse(
        @Schema(description = "Unique ID of the animal", example = "1")
        Long id,
        @Schema(description = "Animal's name", example = "Luna")
        String name,
        @Schema(description = "Kind of animal", example = "CAT")
        AnimalType type,
        @Schema(description = "Breed, if known", example = "Bengal")
        String breed,
        @Schema(description = "Age in years", example = "2")
        Integer age,
        @Schema(description = "Short description of the animal's personality", example = "Calm and affectionate. Loves cuddles.")
        String description,
        @Schema(description = "Adoption status", example = "AVAILABLE")
        AnimalStatus status,
        @Schema(description = "URL path or full URL of the animal's photo", example = "/images/animals/Luna.jpeg")
        String imageUrl,
        @Schema(description = "Set only for adopted animals", example = "adopted on 2026-10-23")
        String adoptionNote
) {}
