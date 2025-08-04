package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.PropertyResponseDTO;
import com.codingplayground.propertyrental.repository.AmenityRepository;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import com.codingplayground.propertyrental.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {

    @Mock
    PropertyRepository propertyRepository;
    @Mock
    AppUserRepository appUserRepository;
    @Mock
    AmenityRepository amenityRepository;

    @InjectMocks
    PropertyServiceImpl propertyService;

    @Test
    void testGetAllProperties() {
        Property property = new Property();
        property.setId(1L);
        property.setTitle("Test Home");
        property.setLocation("Lagos");
        property.setPrice(1000);
        property.setAmenities(List.of());

        when(propertyRepository.findAll()).thenReturn(List.of(property));

        List<PropertyResponseDTO> results = propertyService.getAllProperties();

        assertEquals(1, results.size());
        assertEquals("Test Home", results.get(0).title());
    }
}