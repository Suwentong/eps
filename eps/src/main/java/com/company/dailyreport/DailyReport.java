package com.company.dailyreport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_daily_report")
public class DailyReport {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer studentId;
    private String email;
    private LocalDate date;
    private Integer status;

    private String physicalCondition;
    private List<String> symptoms;
    private double temperature;
    private String inChinaOrNot;
    private String inShanghaiOrNot;
    private List<String> campusesToEnter;
    private String stayInCampusOrNot;
    private String campusToStay;
    private String enterSchoolOrNot;
    private String province;
    private String city;
    private String county;
    private String country;
    private String address;
    private String isHomeAddress;
}
