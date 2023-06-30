package com.company.leaveschool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveSchoolRepository extends JpaRepository<LeaveSchool, Integer> {

    @Query
    LeaveSchool findByStudentId(Integer studentId);

    @Query
    List<LeaveSchool> findAllByStudentId(Integer studentId);
}
