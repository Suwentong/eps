package com.company.dailyreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/daily-report")
public class DailyReportController {

    private final DailyReportService dailyReportService;

    @Autowired
    public DailyReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @GetMapping
    public List<DailyReport> getDailyReports() {
        return dailyReportService.getDailyReports();
    }

    @GetMapping(path = "/{studentId}")
    public List<DailyReport> getDailyReportsByStudentId(@PathVariable("studentId") Integer studentId) {
        return dailyReportService.getDailyReportsByStudentId(studentId);
    }

    @GetMapping(path = "{studentId}/" + "{date}" )
    public DailyReport getDailyReportByDate(@PathVariable("studentId") Integer studentId,
                                            @PathVariable("date") String date) {
        System.out.println(date);
        List<DailyReport> dailyReportList = getDailyReportsByStudentId(studentId);
        for (DailyReport dailyReport: dailyReportList) {
            if (dailyReport.getDate().toString().equals(date))
                return dailyReport;
        }
        return null;
    }

    @PostMapping("/submit")
    public void addNewDailyReport(@RequestBody DailyReport dailyReport) {
        dailyReportService.addNewDailyReport(dailyReport);
    }
}
