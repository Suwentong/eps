package com.company.leaveschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveSchoolService {

    private final LeaveSchoolRepository leaveSchoolRepository;

    @Autowired
    public LeaveSchoolService(LeaveSchoolRepository leaveSchoolRepository) {
        this.leaveSchoolRepository = leaveSchoolRepository;
    }

    public List<LeaveSchool> getLeaveSchoolApplications() { return leaveSchoolRepository.findAll(); }

    public List<LeaveSchool> getLeaveSchoolApplicationsByStudentId(Integer studentId) {
        return leaveSchoolRepository.findAllByStudentId(studentId);
    }

    public void addNewLeaveSchoolApplication(LeaveSchool leaveSchool) {
        var newLeaveSchoolApplication = LeaveSchool.builder()
                .username(leaveSchool.getUsername())
                .studentId(leaveSchool.getStudentId())
                .email(leaveSchool.getEmail())
                .department(leaveSchool.getDepartment())
                .leaveDate(leaveSchool.getLeaveDate())
                .enterDate(leaveSchool.getEnterDate())
                .campus(leaveSchool.getCampus())
                .gate(leaveSchool.getGate())
                .applicationReason(leaveSchool.getApplicationReason())
                .travelTrajectory(leaveSchool.getTravelTrajectory())
                .transportation(leaveSchool.getTransportation())
                .applicationType(leaveSchool.getApplicationType())
                .submitDate(leaveSchool.getSubmitDate())
                .status(0)
                .build();

        leaveSchoolRepository.save(newLeaveSchoolApplication);
    }

    public void changeStatus(Integer studentId, LocalDateTime submitDate, Integer status) {
        List<LeaveSchool> leaveSchoolList = leaveSchoolRepository.findAllByStudentId(studentId);
        for (LeaveSchool leaveSchool : leaveSchoolList) {
            if (leaveSchool.getSubmitDate().toString().equals(submitDate.toString())) {
                leaveSchool.setStatus(status);
                leaveSchoolRepository.save(leaveSchool);
                break;
            }
        }
    }
}
