package com.codingplayground.propertyrental.controller;

import com.codingplayground.propertyrental.model.dto.InquiryDTO;
import com.codingplayground.propertyrental.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inquiries")
@Tag(name = "Inquiries", description = "Endpoints for property rental inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @Operation(summary = "Create an inquiry")
    @PostMapping
    public ResponseEntity<InquiryDTO> createInquiry(@RequestBody @Valid InquiryDTO dto) {
        InquiryDTO created = inquiryService.createInquiry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Get all inquiries for a property")
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<InquiryDTO>> getInquiriesForProperty(@PathVariable Long propertyId) {
        return ResponseEntity.ok(inquiryService.getInquiriesByPropertyId(propertyId));
    }
}
