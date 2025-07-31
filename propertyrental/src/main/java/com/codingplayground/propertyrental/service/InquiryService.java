package com.codingplayground.propertyrental.service;

import com.codingplayground.propertyrental.model.dto.InquiryDTO;

import java.util.List;

public interface InquiryService {
    InquiryDTO createInquiry(InquiryDTO dto);
    List<InquiryDTO> getInquiriesByPropertyId(Long propertyId);
}
