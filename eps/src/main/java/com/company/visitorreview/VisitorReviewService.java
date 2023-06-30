package com.company.visitorreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorReviewService {

    private final VisitorReviewRepository visitorReviewRepository;

    @Autowired
    public VisitorReviewService(VisitorReviewRepository visitorReviewRepository) {
        this.visitorReviewRepository = visitorReviewRepository;
    }

    public List<VisitorReview> getVisitorApplications() { return visitorReviewRepository.findAll(); }

    public List<VisitorReview> getVisitorApplicationsByStudentId(Integer studentId) {
        return visitorReviewRepository.findAllByStudentId(studentId);
    }

    public void addNewVisitorApplication(VisitorReview visitorReview) {
        var newVisitorApplication = VisitorReview.builder()
                .username(visitorReview.getUsername())
                .studentId(visitorReview.getStudentId())
                .email(visitorReview.getEmail())
                .department(visitorReview.getDepartment())
                .visitorName(visitorReview.getVisitorName())
                .idNumber(visitorReview.getIdNumber())
                .phoneNumber(visitorReview.getIdNumber())
                .campus(visitorReview.getCampus())
                .gate(visitorReview.getGate())
                .startDate(visitorReview.getStartDate())
                .endDate(visitorReview.getEndDate())
                .submitDate(visitorReview.getSubmitDate())
                .status(0)
                .build();

        visitorReviewRepository.save(newVisitorApplication);
    }

    public void changeStatus(Integer studentId, LocalDateTime submitDate, Integer status) {
        List<VisitorReview> visitorReviewList = visitorReviewRepository.findAllByStudentId(studentId);
        for (VisitorReview visitorReview : visitorReviewList) {
            if (visitorReview.getSubmitDate().toString().equals(submitDate.toString())) {
                visitorReview.setStatus(status);
                visitorReviewRepository.save(visitorReview);
                break;
            }
        }
    }
}
