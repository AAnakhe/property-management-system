package com.codingplayground.propertyrental.auth.service;

import com.codingplayground.propertyrental.auth.dto.LoginRequest;
import com.codingplayground.propertyrental.auth.jwt.JwtTokenProvider;
import com.codingplayground.propertyrental.enums.ErrorType;
import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import com.codingplayground.propertyrental.util.ApiErrorResponse;
import com.codingplayground.propertyrental.util.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class AuthService extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AppUserRepository appUserRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);

            String identifier = loginRequest.email() != null && !loginRequest.email().isBlank()
                    ? loginRequest.email()
                    : loginRequest.phone();

            if (identifier == null || identifier.isBlank()) {
                throw new AuthenticationServiceException("Email or phone number is required.");
            }

            var authToken = new UsernamePasswordAuthenticationToken(identifier, loginRequest.password());
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            log.error("Invalid login request format: {}", e.getMessage(), e);
            throw new AuthenticationServiceException("Invalid request payload");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException {
        String identifier = authResult.getName();
        log.info("Looking up user by identifier: {}", identifier);

        UUID userId = UUID.fromString(authResult.getName());
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        log.info("Authenticated request from userId={} ({}{})",
                user.getId(),
                user.getEmail() != null ? "email: " + user.getEmail() : "",
                user.getPhone() != null ? " phone: " + user.getPhone() : "");

        var token = tokenProvider.accessToken(user, authResult);
        var result = new ApiResponse(true, Map.of(
                "access_token", token,
                "user", Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "role", user.getRoleAsAuthority(),
                        "firstName", user.getFirstName(),
                        "lastName", user.getLastName()
                )
        ), "Login successful");

        writeJsonResponse(response, HttpServletResponse.SC_OK, result);
        SecurityContextHolder.getContext().setAuthentication(authResult);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        String errorMessage;

        if (failed instanceof InternalAuthenticationServiceException && failed.getCause() != null) {

            errorMessage = failed.getCause().getMessage();
        } else {
            errorMessage = switch (failed.getClass().getSimpleName()) {
                case "BadCredentialsException" -> "Invalid email or password.";
                case "UsernameNotFoundException" -> "User not found.";
                case "DisabledException" -> failed.getMessage();
                default -> "Authentication failed.";
            };
        }

        log.warn("Authentication failed: {}", errorMessage);
        var errorResponse = new ApiErrorResponse(false, null, errorMessage, ErrorType.ERROR);
        writeJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, errorResponse);
    }

    private void writeJsonResponse(HttpServletResponse response, int status, Object body) {
        response.setStatus(status);
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(body));
        } catch (IOException e) {
            log.error("Error writing JSON response: {}", e.getMessage(), e);
        }
    }
}

