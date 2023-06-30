package com.company.dailyreport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Integer> {

    @Query
    Optional<DailyReport> findByEmail(String email);

    @Query
    List<DailyReport> findAllByStudentId(Integer studentId);

    @Query
    DailyReport findByDate(LocalDate date);
}
