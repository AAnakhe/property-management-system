package com.codingplayground.propertyrental.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InquiryDTO(

        @Schema(description = "Inquiry ID", example = "1")
        Long id,

        @Schema(description = "ID of the property being inquired about", example = "5")
        @NotNull(message = "Property ID is required")
        Long propertyId,

        @Schema(description = "Message from the potential tenant", example = "I would like to know if the apartment is still available.")
        @NotBlank(message = "Message is required")
        String message,

        @Schema(description = "Contact email of the tenant", example = "tenant@example.com")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Contact email is required")
        String contactEmail
) {}
