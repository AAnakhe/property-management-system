package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.exception.ResourceNotFoundException;
import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.model.dto.AppUserRequestDTO;
import com.codingplayground.propertyrental.model.dto.AppUserResponseDTO;
import com.codingplayground.propertyrental.model.enums.Role;
import com.codingplayground.propertyrental.model.mapper.AppUserMapper;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import com.codingplayground.propertyrental.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUserResponseDTO registerUser(AppUserRequestDTO dto) {
        AppUser entity = new AppUser();
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setPassword(passwordEncoder.encode(dto.password()));
        entity.setPhone(dto.phone());
        entity.setRole(Role.LANDLORD);
        AppUser saved = appUserRepository.save(entity);
        return appUserMapper.toDto(saved);
    }

    @Override
    public AppUserResponseDTO getUserById(UUID id) {
        AppUser user = appUserRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
        return appUserMapper.toDto(user);
    }

    @Override
    public List<AppUserResponseDTO> getAllUsers() {
        return appUserRepository.findAll().stream()
                .map(appUserMapper::toDto)
                .toList();
    }

    @Override
    public void deleteUser(UUID id) {
        if (!appUserRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        appUserRepository.deleteById(id);
    }
}
