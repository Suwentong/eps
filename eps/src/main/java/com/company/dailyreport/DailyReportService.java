package com.company.dailyreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailyReportService {

    private final DailyReportRepository dailyReportRepository;

    @Autowired
    public DailyReportService(DailyReportRepository dailyReportRepository) {
        this.dailyReportRepository = dailyReportRepository;
    }

    public List<DailyReport> getDailyReports() {
        return dailyReportRepository.findAll();
    }

    public List<DailyReport> getDailyReportsByStudentId(Integer studentId) {
        return dailyReportRepository.findAllByStudentId(studentId);
    }

    public DailyReport getDailyReportByDate(LocalDate date) {
        return dailyReportRepository.findByDate(date);
    }

    public void addNewDailyReport(DailyReport dailyReport) {
        var newDailyReport = DailyReport.builder()
                        .studentId(dailyReport.getStudentId())
                        .email(dailyReport.getEmail())
                        .date(dailyReport.getDate())
                        .physicalCondition(dailyReport.getPhysicalCondition())
                        .symptoms(dailyReport.getSymptoms())
                        .temperature(dailyReport.getTemperature())
                        .inChinaOrNot(dailyReport.getInChinaOrNot())
                        .inShanghaiOrNot(dailyReport.getInShanghaiOrNot())
                        .campusesToEnter(dailyReport.getCampusesToEnter())
                        .stayInCampusOrNot(dailyReport.getStayInCampusOrNot())
                        .campusToStay(dailyReport.getCampusToStay())
                        .enterSchoolOrNot(dailyReport.getEnterSchoolOrNot())
                        .province(dailyReport.getProvince())
                        .city(dailyReport.getCity())
                        .county(dailyReport.getCounty())
                        .country(dailyReport.getCountry())
                        .address(dailyReport.getAddress())
                        .isHomeAddress(dailyReport.getIsHomeAddress())
                        .status(1)
                        .build();

        dailyReportRepository.save(newDailyReport);
    }
}
