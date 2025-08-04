package com.codingplayground.propertyrental.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record PropertyRequestDTO(

        @Schema(description = "Title of the property", example = "Spacious 2-bedroom apartment")
        @NotBlank(message = "Title is required")
        String title,

        @Schema(description = "Location of the property", example = "Lagos, Nigeria")
        @NotBlank(message = "Location is required")
        String location,

        @Schema(description = "Monthly rental price", example = "75000")
        @Positive(message = "Price must be a positive number")
        double price,

        @Schema(description = "Owner's UUID", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
        @NotNull(message = "Owner ID is required")
        UUID ownerId,

        @Schema(description = "List of amenity names", example = "[\"WiFi\", \"Parking\"]")
        List<String> amenities
) {}
