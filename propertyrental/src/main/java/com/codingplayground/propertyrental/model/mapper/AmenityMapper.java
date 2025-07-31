package com.codingplayground.propertyrental.model.mapper;

import com.codingplayground.propertyrental.model.Amenity;
import com.codingplayground.propertyrental.model.dto.AmenityDTO;

public class AmenityMapper {

    public static AmenityDTO toDTO(Amenity entity) {
        return new AmenityDTO(
                entity.getId(),
                entity.getName()
        );
    }

    public static Amenity toEntity(AmenityDTO dto) {
        Amenity amenity = new Amenity();
        amenity.setName(dto.name());
        return amenity;
    }
}
