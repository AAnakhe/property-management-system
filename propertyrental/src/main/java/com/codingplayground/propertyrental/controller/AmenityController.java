package com.codingplayground.propertyrental.controller;

import com.codingplayground.propertyrental.model.dto.AmenityDTO;
import com.codingplayground.propertyrental.service.AmenityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
@Tag(name = "Amenities", description = "Operations related to property amenities")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @Operation(summary = "Get all amenities")
    @GetMapping
    public ResponseEntity<List<AmenityDTO>> getAll() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    @Operation(summary = "Get an amenity by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AmenityDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(amenityService.getAmenityById(id));
    }

   /* @Operation(summary = "Create a new amenity")
    @PostMapping
    public ResponseEntity<AmenityDTO> create(@RequestBody @Valid AmenityDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityService.createAmenity(dto));
    }*/

    @Operation(summary = "Delete an amenity by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }
}

