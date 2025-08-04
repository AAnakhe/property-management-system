package com.codingplayground.propertyrental.service;

import com.codingplayground.propertyrental.model.dto.PropertyRequestDTO;
import com.codingplayground.propertyrental.model.dto.PropertyResponseDTO;

import java.util.List;

public interface PropertyService {
    List<PropertyResponseDTO> getAllProperties();
    PropertyResponseDTO createProperty(PropertyRequestDTO dto);
    PropertyResponseDTO getPropertyById(Long id);
    void deleteProperty(Long id);
}
