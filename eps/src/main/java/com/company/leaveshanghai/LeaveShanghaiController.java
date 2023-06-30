package com.company.leaveshanghai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-shanghai")
public class LeaveShanghaiController {

    private final LeaveShanghaiService leaveShanghaiService;

    @Autowired
    public LeaveShanghaiController(LeaveShanghaiService leaveShanghaiService) {
        this.leaveShanghaiService = leaveShanghaiService;
    }

    @GetMapping
    public List<LeaveShanghai> getLeaveShanghaiApplications() { return leaveShanghaiService.getLeaveShanghaiApplications(); }

    @GetMapping(path = "/{studentId}")
    public List<LeaveShanghai> getLeaveShanghaiApplicationsByStudentId(@PathVariable("studentId") Integer studentId) {
        return leaveShanghaiService.getLeaveShanghaiApplicationsByStudentId(studentId);
    }

    @PostMapping("/submit")
    public void addNewLeaveShanghaiApplications(@RequestBody LeaveShanghai leaveShanghai) {
        leaveShanghaiService.addNewLeaveShanghaiApplication(leaveShanghai);
    }

    @PutMapping(path = "/{studentId}")
    public void changeStatus(@PathVariable("studentId") Integer studentId,
                             @RequestParam(required = false) LocalDateTime submitDate,
                             @RequestParam(required = false) Integer status) {
        leaveShanghaiService.changeStatus(studentId, submitDate, status);
    }
}
