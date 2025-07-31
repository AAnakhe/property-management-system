package com.codingplayground.propertyrental.service;

import com.codingplayground.propertyrental.model.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
    List<PropertyDTO> getAllProperties();
    PropertyDTO createProperty(PropertyDTO dto);
    PropertyDTO getPropertyById(Long id);
    void deleteProperty(Long id);
}
