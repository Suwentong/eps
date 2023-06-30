package com.company.auth;

import com.company.config.JwtService;
import com.company.user.Role;
import com.company.user.User;
import com.company.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunnerForAuth(UserRepository userRepository) {
        return args -> {
            var user = User.builder()
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .password(passwordEncoder.encode("1234"))
                    .department("计算机学院")
                    .studentId(19160079)
                    .role(Role.USER)
                    .build();

            var admin = User.builder()
                    .username("admin")
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.saveAll(List.of(user, admin));
        };
    }
}
