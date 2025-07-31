package com.codingplayground.propertyrental.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AmenityDTO(

        @Schema(description = "Amenity ID", example = "1")
        Long id,

        @Schema(description = "Name of the amenity", example = "Air Conditioning")
        @NotBlank(message = "Amenity name is required")
        String name
) {}
