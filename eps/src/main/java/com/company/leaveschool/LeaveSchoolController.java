package com.company.leaveschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-school")
public class LeaveSchoolController {

    private final LeaveSchoolService leaveSchoolService;

    @Autowired
    public LeaveSchoolController(LeaveSchoolService leaveSchoolService) {
        this.leaveSchoolService = leaveSchoolService;
    }

    @GetMapping
    public List<LeaveSchool> getLeaveSchoolApplications() { return leaveSchoolService.getLeaveSchoolApplications(); }

    @GetMapping(path = "/{studentId}")
    public List<LeaveSchool> getLeaveSchoolApplicationsByStudentId(@PathVariable("studentId") Integer studentId) {
        return leaveSchoolService.getLeaveSchoolApplicationsByStudentId(studentId);
    }

    @PostMapping("/submit")
    public void addNewLeaveSchoolApplication(@RequestBody LeaveSchool leaveSchool) {
        leaveSchoolService.addNewLeaveSchoolApplication(leaveSchool);
    }

    @PutMapping(path = "/{studentId}")
    public void changeStatus(@PathVariable("studentId") Integer studentId,
                             @RequestParam(required = false) LocalDateTime submitDate,
                             @RequestParam(required = false) Integer status) {
        leaveSchoolService.changeStatus(studentId, submitDate, status);
    }
}
