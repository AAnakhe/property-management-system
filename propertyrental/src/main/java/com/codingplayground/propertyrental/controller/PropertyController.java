package com.codingplayground.propertyrental.controller;

import com.codingplayground.propertyrental.model.dto.PropertyDTO;
import com.codingplayground.propertyrental.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
@Tag(name = "Properties", description = "Endpoints for property management")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Operation(summary = "Get all properties")
    @GetMapping
    public List<PropertyDTO> getAll() {
        return propertyService.getAllProperties();
    }

    @Operation(summary = "Get property by ID")
    @GetMapping("/{id}")
    public PropertyDTO getById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @Operation(summary = "Create a new property")
    @PostMapping
    public PropertyDTO create(@RequestBody @Valid PropertyDTO dto) {
        return propertyService.createProperty(dto);
    }

    @Operation(summary = "Delete property by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}

