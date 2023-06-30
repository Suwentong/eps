package com.company.leaveshanghai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveShanghaiService {

    private final LeaveShanghaiRepository leaveShanghaiRepository;

    @Autowired
    public LeaveShanghaiService(LeaveShanghaiRepository leaveShanghaiRepository) {
        this.leaveShanghaiRepository = leaveShanghaiRepository;
    }

    public List<LeaveShanghai> getLeaveShanghaiApplications() { return leaveShanghaiRepository.findAll(); }

    public List<LeaveShanghai> getLeaveShanghaiApplicationsByStudentId(Integer studentId) {
        return leaveShanghaiRepository.findAllByStudentId(studentId);
    }

    public void addNewLeaveShanghaiApplication(LeaveShanghai leaveShanghai) {
        var newLeaveShanghaiApplication = LeaveShanghai.builder()
                .username(leaveShanghai.getUsername())
                .studentId(leaveShanghai.getStudentId())
                .email(leaveShanghai.getEmail())
                .department(leaveShanghai.getDepartment())
                .leaveDate(leaveShanghai.getLeaveDate())
                .enterDate(leaveShanghai.getEnterDate())
                .province(leaveShanghai.getProvince())
                .city(leaveShanghai.getCity())
                .destinationAddress(leaveShanghai.getDestinationAddress())
                .applicationReason(leaveShanghai.getApplicationReason())
                .travelTrajectory(leaveShanghai.getTravelTrajectory())
                .transportation(leaveShanghai.getTransportation())
                .applicationType(leaveShanghai.getApplicationType())
                .submitDate(leaveShanghai.getSubmitDate())
                .status(0)
                .build();

        leaveShanghaiRepository.save(newLeaveShanghaiApplication);
    }

    public void changeStatus(Integer studentId, LocalDateTime submitDate, Integer status) {
        List<LeaveShanghai> leaveShanghaiList = leaveShanghaiRepository.findAllByStudentId(studentId);
        for (LeaveShanghai leaveShanghai : leaveShanghaiList) {
            if (leaveShanghai.getSubmitDate().toString().equals(submitDate.toString())) {
                leaveShanghai.setStatus(status);
                leaveShanghaiRepository.save(leaveShanghai);
                break;
            }
        }
    }
}
