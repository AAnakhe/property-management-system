package com.codingplayground.propertyrental.model.mapper;

import com.codingplayground.propertyrental.model.Amenity;
import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.PropertyDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static PropertyDTO toDTO(Property entity) {
        return new PropertyDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getLocation(),
                entity.getPrice(),
                entity.getOwner() != null ? entity.getOwner().getId() : null,
                entity.getAmenities() != null ?
                        entity.getAmenities().stream().map(Amenity::getName).collect(Collectors.toList())
                        : List.of()
        );
    }

    public static Property toEntity(PropertyDTO dto) {
        Property property = new Property();
        property.setTitle(dto.title());
        property.setLocation(dto.location());
        property.setPrice(dto.price());
        return property;
    }
}
