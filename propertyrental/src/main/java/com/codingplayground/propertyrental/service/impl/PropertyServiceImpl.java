package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.exception.ResourceNotFoundException;
import com.codingplayground.propertyrental.model.Amenity;
import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.PropertyDTO;
import com.codingplayground.propertyrental.model.mapper.PropertyMapper;
import com.codingplayground.propertyrental.repository.AmenityRepository;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import com.codingplayground.propertyrental.repository.PropertyRepository;
import com.codingplayground.propertyrental.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final AppUserRepository appUserRepository;
    private final AmenityRepository amenityRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository,
                               AppUserRepository appUserRepository,
                               AmenityRepository amenityRepository) {
        this.propertyRepository = propertyRepository;
        this.appUserRepository = appUserRepository;
        this.amenityRepository = amenityRepository;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO dto) {
        AppUser owner = appUserRepository.findById(dto.ownerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + dto.ownerId()));

        List<Amenity> amenities = new ArrayList<>();
        if (dto.amenities() != null) {
            amenities = amenityRepository.findAll().stream()
                    .filter(a -> dto.amenities().contains(a.getName()))
                    .collect(Collectors.toList());
        }

        Property property = PropertyMapper.toEntity(dto);
        property.setOwner(owner);
        property.setAmenities(amenities);

        Property saved = propertyRepository.save(property);
        return PropertyMapper.toDTO(saved);
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
        return PropertyMapper.toDTO(property);
    }

    @Override
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Property not found with ID: " + id);
        }
        propertyRepository.deleteById(id);
    }
}
