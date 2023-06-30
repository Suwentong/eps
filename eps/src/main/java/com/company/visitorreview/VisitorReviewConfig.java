package com.company.visitorreview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class VisitorReviewConfig {

    @Bean
    CommandLineRunner commandLineRunnerForVR(VisitorReviewRepository visitorReviewRepository) {
        return args -> {
            var vr1 = VisitorReview.builder()
                    .startDate(LocalDateTime.of(2023,4,11,10,0,0))
                    .endDate(LocalDateTime.of(2023,4,11,20,0,0))
                    .submitDate(LocalDateTime.of(2023,4,11,8,0,0))
                    .visitorName("Ansor")
                    .idNumber("401587624")
                    .phoneNumber("17111896777")
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .studentId(19160079)
                    .department("计算机学院")
                    .status(0)
                    .build();

            var vr2 = VisitorReview.builder()
                    .startDate(LocalDateTime.of(2023,4,11,10,0,0))
                    .endDate(LocalDateTime.of(2023,4,11,20,0,0))
                    .submitDate(LocalDateTime.of(2023,4,14,8,0,0))
                    .visitorName("Rahmat")
                    .idNumber("753954185")
                    .phoneNumber("15221028568")
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .studentId(19160079)
                    .department("计算机学院")
                    .status(1)
                    .build();

            var vr3 = VisitorReview.builder()
                    .startDate(LocalDateTime.of(2023,4,11,10,0,0))
                    .endDate(LocalDateTime.of(2023,4,11,20,0,0))
                    .submitDate(LocalDateTime.of(2023,4,17,8,0,0))
                    .visitorName("Romin")
                    .idNumber("382859255")
                    .phoneNumber("15285939591")
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .studentId(19160079)
                    .department("计算机学院")
                    .status(2)
                    .build();

            visitorReviewRepository.saveAll(List.of(vr1, vr2, vr3));
        };
    }
}
