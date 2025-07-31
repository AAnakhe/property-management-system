package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.exception.ResourceNotFoundException;
import com.codingplayground.propertyrental.model.Inquiry;
import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.InquiryDTO;
import com.codingplayground.propertyrental.model.mapper.InquiryMapper;
import com.codingplayground.propertyrental.repository.InquiryRepository;
import com.codingplayground.propertyrental.repository.PropertyRepository;
import com.codingplayground.propertyrental.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public InquiryDTO createInquiry(InquiryDTO dto) {
        Property property = propertyRepository.findById(dto.propertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + dto.propertyId()));

        Inquiry inquiry = InquiryMapper.toEntity(dto, property);
        Inquiry saved = inquiryRepository.save(inquiry);
        return InquiryMapper.toDTO(saved);
    }

    @Override
    public List<InquiryDTO> getInquiriesByPropertyId(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + propertyId));

        return inquiryRepository.findAll().stream()
                .filter(i -> i.getProperty().equals(property))
                .map(InquiryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
