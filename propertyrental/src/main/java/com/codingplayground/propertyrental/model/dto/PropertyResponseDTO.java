package com.codingplayground.propertyrental.model.dto;

import java.util.List;
import java.util.UUID;

public record PropertyResponseDTO(
        Long id,
        String title,
        double price,
        UUID ownerId,
        List<String> amenities
) {}
