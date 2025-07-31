package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.exception.ResourceNotFoundException;
import com.codingplayground.propertyrental.model.Amenity;
import com.codingplayground.propertyrental.model.dto.AmenityDTO;
import com.codingplayground.propertyrental.model.mapper.AmenityMapper;
import com.codingplayground.propertyrental.repository.AmenityRepository;
import com.codingplayground.propertyrental.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository repository;


    @Override
    public List<AmenityDTO> getAllAmenities() {
        return repository.findAll().stream()
                .map(AmenityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AmenityDTO getAmenityById(Long id) {
        Amenity amenity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found with id " + id));
        return AmenityMapper.toDTO(amenity);
    }

    @Override
    public AmenityDTO createAmenity(AmenityDTO dto) {
        Amenity amenity = AmenityMapper.toEntity(dto);
        return AmenityMapper.toDTO(repository.save(amenity));
    }

    @Override
    public void deleteAmenity(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Amenity not found with id " + id);
        }
        repository.deleteById(id);
    }
}
