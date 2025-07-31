package com.codingplayground.propertyrental.controller;

import com.codingplayground.propertyrental.model.dto.AppUserRequestDTO;
import com.codingplayground.propertyrental.model.dto.AppUserResponseDTO;
import com.codingplayground.propertyrental.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "User registration and management")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<AppUserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(appUserService.getAllUsers());
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AppUserResponseDTO> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(appUserService.getUserById(id));
    }

    @Operation(summary = "Register a new user")
    @PostMapping
    public ResponseEntity<AppUserResponseDTO> register(@RequestBody @Valid AppUserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.registerUser(dto));
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
