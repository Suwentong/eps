package com.company.leaveschool;

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
@Table(name = "_leave_school")
public class LeaveSchool {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer status;
    private String username;
    private Integer studentId;
    private String email;
    private String department;

    private LocalDateTime leaveDate;
    private LocalDateTime enterDate;
    private String campus;
    private String gate;
    private String applicationReason;
    private String travelTrajectory;
    private String transportation;
    private String applicationType;
    private LocalDateTime submitDate;
}
