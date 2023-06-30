import axios from 'axios';

const API_URL = '/api/v1/daily-report';

class DailyReportService {
    submit(dailyreport) {
        return axios
            .post(API_URL + '/submit', {
                studentId: dailyreport.studentId,
                email: dailyreport.email,
                date: dailyreport.date,

                physicalCondition: dailyreport.physicalCondition,
                symptoms: dailyreport.symptoms,
                temperature: dailyreport.temperature,
                inChinaOrNot: dailyreport.inChinaOrNot,
                inShanghaiOrNot: dailyreport.inShanghaiOrNot,
                campusesToEnter: dailyreport.campusesToEnter,
                stayInCampusOrNot: dailyreport.stayInCampusOrNot,
                campusToStay: dailyreport.campusToStay,
                enterSchoolOrNot: dailyreport.enterSchoolOrNot,
                province: dailyreport.province,
                city: dailyreport.city,
                county: dailyreport.county,
                country: dailyreport.country,
                address: dailyreport.address,
                isHomeAddress: dailyreport.isHomeAddress
            })
            .then(response => {
                console.log(response)
            })
    }
    getAll() {
        return axios
            .get(API_URL)
            .then(response => {
                console.log(response)
                return response;
            })
    }
    getAllByStudentId(studentId) {
        return axios
            .get(API_URL + "/" + studentId, {
                studentId: studentId
            })
            .then(response => {
                console.log(response)
                return response;
            })
    }
    getByDate(studentId, date) {
        console.log(studentId)
        console.log(date)
        return axios
            .get(API_URL + "/" + studentId + "/" + date)
            .then(response => {
                return response;
            })
    }
}

export default new DailyReportService();