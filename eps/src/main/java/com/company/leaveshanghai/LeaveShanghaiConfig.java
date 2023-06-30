package com.company.leaveshanghai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class LeaveShanghaiConfig {

    @Bean
    CommandLineRunner commandLineRunnerForLSH(LeaveShanghaiRepository leaveShanghaiRepository) {
        return args -> {
            var ls1 = LeaveShanghai.builder()
                    .leaveDate(LocalDateTime.of(2023,4,11,10,0,0))
                    .enterDate(LocalDateTime.of(2023,4,11,20,0,0))
                    .submitDate(LocalDateTime.of(2023,4,11,8,0,0))
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .department("计算机学院")
                    .studentId(19160079)
                    .applicationType("leave")
                    .status(2)
                    .build();

            var ls2 = LeaveShanghai.builder()
                    .leaveDate(LocalDateTime.of(2023,4,12,10,0,0))
                    .enterDate(LocalDateTime.of(2023,4,12,20,0,0))
                    .submitDate(LocalDateTime.of(2023,4,12,8,0,0))
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .department("计算机学院")
                    .studentId(19160079)
                    .applicationType("leave")
                    .status(1)
                    .build();

            var ls3 = LeaveShanghai.builder()
                    .enterDate(LocalDateTime.of(2023,4,12,19,30,0))
                    .submitDate(LocalDateTime.of(2023,4,12,19,0,0))
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .department("计算机学院")
                    .studentId(19160079)
                    .applicationType("return")
                    .status(1)
                    .build();

            var ls4 = LeaveShanghai.builder()
                    .leaveDate(LocalDateTime.of(2023,4,13,10,0,0))
                    .enterDate(LocalDateTime.of(2023,4,13,20,0,0))
                    .submitDate(LocalDateTime.of(2023,4,13,8,0,0))
                    .username("Sulton 苏文通")
                    .email("abdukadyrovs@mail.com")
                    .department("计算机学院")
                    .studentId(19160079)
                    .applicationType("leave")
                    .status(0)
                    .build();

            leaveShanghaiRepository.saveAll(List.of(ls1, ls2, ls3, ls4));
        };
    }
}
