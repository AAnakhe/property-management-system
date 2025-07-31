package com.codingplayground.propertyrental.model.mapper;

import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.model.dto.AppUserRequestDTO;
import com.codingplayground.propertyrental.model.dto.AppUserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUserResponseDTO toDto(AppUser entity) {
        return new AppUserResponseDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getRole()
        );
    }

    public AppUser toEntity(AppUserRequestDTO dto) {
        AppUser user = new AppUser();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setPassword(dto.password());
        return user;
    }
}

