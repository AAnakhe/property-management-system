package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.model.Inquiry;
import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.InquiryDTO;
import com.codingplayground.propertyrental.repository.InquiryRepository;
import com.codingplayground.propertyrental.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InquiryServiceImplTest {

    @Mock
    InquiryRepository inquiryRepository;
    @Mock
    PropertyRepository propertyRepository;
    @InjectMocks
    InquiryServiceImpl inquiryService;

    @Test
    void testCreateInquiry() {
        Property property = new Property();
        property.setId(10L);

        InquiryDTO dto = new InquiryDTO(null, 10L, "Is it available?", "someone@example.com");
        Inquiry inquiry = new Inquiry();
        inquiry.setId(1L);
        inquiry.setMessage("Is it available?");
        inquiry.setContactEmail("someone@example.com");
        inquiry.setProperty(property);

        when(propertyRepository.findById(10L)).thenReturn(Optional.of(property));
        when(inquiryRepository.save(any())).thenReturn(inquiry);

        InquiryDTO result = inquiryService.createInquiry(dto);

        assertEquals("Is it available?", result.message());
    }
}