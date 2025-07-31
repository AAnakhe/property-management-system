package com.codingplayground.propertyrental.model.dto;

import com.codingplayground.propertyrental.model.enums.Role;

import java.util.UUID;

public record AppUserResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Role role

) {}
