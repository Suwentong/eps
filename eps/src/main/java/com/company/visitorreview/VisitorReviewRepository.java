package com.company.visitorreview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitorReviewRepository extends JpaRepository<VisitorReview, Integer> {

    @Query
    List<VisitorReview> findAllByStudentId(Integer studentId);
}
