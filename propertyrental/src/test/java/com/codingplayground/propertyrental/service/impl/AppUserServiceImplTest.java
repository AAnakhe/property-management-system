package com.codingplayground.propertyrental.service.impl;

import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.model.dto.AppUserRequestDTO;
import com.codingplayground.propertyrental.model.dto.AppUserResponseDTO;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    @Mock
    AppUserRepository appUserRepository;
    @InjectMocks
    AppUserServiceImpl appUserService;

    @Test
    void testRegisterUser() {
        AppUserRequestDTO dto = new AppUserRequestDTO("Jane", "Glow","jane@mail.com", "07033918386",
                "password", "password");
        AppUser user = new AppUser();
        user.setId(UUID.randomUUID());
        user.setFirstName("Jane");
        user.setLastName("Glow");
        user.setPhone("07033918386");
        user.setEmail("jane@mail.com");
        user.setPassword("password");

        when(appUserRepository.save(any())).thenReturn(user);

        AppUserResponseDTO result = appUserService.registerUser(dto);

        assertEquals("Jane", result.firstName());
        assertEquals("Glow", result.lastName());
        assertEquals("jane@mail.com", result.email());
    }
}