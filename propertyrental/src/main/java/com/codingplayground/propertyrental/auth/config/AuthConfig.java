package com.codingplayground.propertyrental.auth.config;

import com.codingplayground.propertyrental.model.AppUser;
import com.codingplayground.propertyrental.repository.AppUserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@Getter
@Configuration
@RequiredArgsConstructor
@DependsOn("entityManagerFactory")
public class AuthConfig {
    private final AppUserRepository needUserRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return identifier -> {
            AppUser user = needUserRepository.findByEmail(identifier)
                    .or(() -> needUserRepository.findByPhone(identifier))
                    .orElseThrow(() -> new UsernameNotFoundException("Invalid email/phone or password."));

            return new User(
                    user.getId().toString(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRoleAsAuthority()))
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


}


