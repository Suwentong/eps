package com.company.visitorreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visitor-review")
public class VisitorReviewController {

    private final VisitorReviewService visitorReviewService;

    @Autowired
    public VisitorReviewController(VisitorReviewService visitorReviewService) {
        this.visitorReviewService = visitorReviewService;
    }

    @GetMapping
    public List<VisitorReview> getVisitorApplications() { return visitorReviewService.getVisitorApplications(); }

    @GetMapping(path = "/{studentId}")
    public List<VisitorReview> getVisitorApplicationsByStudentId(@PathVariable("studentId") Integer studentId) {
        return visitorReviewService.getVisitorApplicationsByStudentId(studentId);
    }

    @PostMapping("/submit")
    public void addNewVisitorApplication(@RequestBody VisitorReview visitorReview) {
        visitorReviewService.addNewVisitorApplication(visitorReview);
    }

    @PutMapping(path = "/{studentId}")
    public void changeStatus(@PathVariable("studentId") Integer studentId,
                             @RequestParam(required = false) LocalDateTime submitDate,
                             @RequestParam(required = false) Integer status) {
        visitorReviewService.changeStatus(studentId, submitDate, status);
    }
}
