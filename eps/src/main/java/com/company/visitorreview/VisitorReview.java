package com.company.visitorreview;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_visitor_review")
public class VisitorReview {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer status;
    private String username;
    private Integer studentId;
    private String email;
    private String department;

    private String visitorName;
    private String idNumber;
    private String phoneNumber;
    private String campus;
    private String gate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime submitDate;
}
