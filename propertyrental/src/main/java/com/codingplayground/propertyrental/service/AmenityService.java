package com.codingplayground.propertyrental.service;

import com.codingplayground.propertyrental.model.dto.AmenityDTO;

import java.util.List;

public interface AmenityService {
    List<AmenityDTO> getAllAmenities();

    AmenityDTO getAmenityById(Long id);

    AmenityDTO createAmenity(AmenityDTO dto);

    void deleteAmenity(Long id);
}
