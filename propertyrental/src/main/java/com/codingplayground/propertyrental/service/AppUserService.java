package com.codingplayground.propertyrental.service;

import com.codingplayground.propertyrental.model.dto.AppUserRequestDTO;
import com.codingplayground.propertyrental.model.dto.AppUserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    AppUserResponseDTO registerUser(AppUserRequestDTO dto);

    AppUserResponseDTO getUserById(UUID id);

    List<AppUserResponseDTO> getAllUsers();

    void deleteUser(UUID id);
}
