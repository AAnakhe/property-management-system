package com.codingplayground.propertyrental.model.mapper;

import com.codingplayground.propertyrental.model.Amenity;
import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.PropertyRequestDTO;
import com.codingplayground.propertyrental.model.dto.PropertyResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static PropertyResponseDTO toDTO(Property entity) {
        return new PropertyResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getPrice(),
                entity.getOwner() != null ? entity.getOwner().getId() : null,
                entity.getAmenities() != null ?
                        entity.getAmenities().stream()
                                .map(Amenity::getName)
                                .collect(Collectors.toList())
                        : List.of()
        );
    }

    public static Property toEntity(PropertyRequestDTO dto) {
        Property property = new Property();
        property.setTitle(dto.title());
        property.setLocation(dto.location());
        property.setPrice(dto.price());
        return property;
    }
}
