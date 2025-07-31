package com.codingplayground.propertyrental.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AppUserRequestDTO(

        @Schema(description = "First name of the customer", example = "Clement")
        @NotBlank(message = "First name is required")
        String firstName,

        @Schema(description = "Last name of the customer", example = "Ori")
        @NotBlank(message = "Last name is required")
        String lastName,

        @Schema(description = "Email address", example = "ada@example.com")
        @Email(message = "Invalid email format")
        String email,


        @Schema(description = "Phone number", example = "08012345678")
        @NotBlank(message = "Phone number is required")
        String phone,

        @Schema(description = "Password", example = "password")
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @Schema(description = "Confirm password", example = "password")
        @NotBlank(message = "Confirm password is required")
        String confirmPassword
) {}
