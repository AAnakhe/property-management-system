package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.model.Amenity;
import com.codingplayground.propertyrental.model.dto.AmenityDTO;
import com.codingplayground.propertyrental.repository.AmenityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmenityServiceImplTest {

    @Mock
    AmenityRepository amenityRepository;
    @InjectMocks
    AmenityServiceImpl amenityService;

    @Test
    void testCreateAmenity() {
        AmenityDTO dto = new AmenityDTO(null, "WiFi");
        Amenity amenity = new Amenity();
        amenity.setId(1L);
        amenity.setName("WiFi");

        when(amenityRepository.save(any())).thenReturn(amenity);

        AmenityDTO result = amenityService.createAmenity(dto);

        assertEquals("WiFi", result.name());
    }
}