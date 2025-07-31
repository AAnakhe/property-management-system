package com.codingplayground.propertyrental.model.mapper;

import com.codingplayground.propertyrental.model.Inquiry;
import com.codingplayground.propertyrental.model.Property;
import com.codingplayground.propertyrental.model.dto.InquiryDTO;

public class InquiryMapper {

    public static InquiryDTO toDTO(Inquiry entity) {
        return new InquiryDTO(
                entity.getId(),
                entity.getProperty() != null ? entity.getProperty().getId() : null,
                entity.getMessage(),
                entity.getContactEmail()
        );
    }

    public static Inquiry toEntity(InquiryDTO dto, Property property) {
        Inquiry inquiry = new Inquiry();
        inquiry.setProperty(property);
        inquiry.setMessage(dto.message());
        inquiry.setContactEmail(dto.contactEmail());
        return inquiry;
    }
}
