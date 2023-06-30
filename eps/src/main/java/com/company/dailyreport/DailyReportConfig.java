package com.company.dailyreport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DailyReportConfig {

    @Bean
    CommandLineRunner commandLineRunnerForDR(DailyReportRepository dailyReportRepository) {
        return args -> {
            var d1 = DailyReport.builder()
                    .studentId(19160079)
                    .email("abdukadyrovs@bk.ru")
                    .date(LocalDate.of(2023, 4, 2))
                    .status(1)
                    .build();

            var d2 = DailyReport.builder()
                    .studentId(19160078)
                    .email("student@mail.com")
                    .date(LocalDate.of(2023, 4, 2))
                    .status(1)
                    .build();

            var d3 = DailyReport.builder()
                    .studentId(19160079)
                    .email("abdukadyrovs@bk.ru")
                    .date(LocalDate.of(2023, 4, 3))
                    .status(1)
                    .build();

            var d4 = DailyReport.builder()
                    .studentId(19160078)
                    .email("student@mail.com")
                    .date(LocalDate.of(2023, 4, 3))
                    .status(1)
                    .build();

            dailyReportRepository.saveAll(List.of(d1, d2, d3, d4));
        };
    }
}
