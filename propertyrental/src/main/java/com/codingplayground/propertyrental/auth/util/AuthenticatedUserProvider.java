package com.codingplayground.propertyrental.auth.util;

import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserProvider {

    private final AppUserRepository needUserRepository;

    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new EntityNotFoundException("No authenticated user found.");
        }

        String userId = authentication.getName();
        return needUserRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found."));
    }

    public UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new EntityNotFoundException("No authenticated user found.");
        }

        return UUID.fromString(authentication.getName());
    }

}
