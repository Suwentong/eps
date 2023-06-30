import axios from 'axios';

const API_URL = '/api/v1/leave-school';

class LeaveSchoolService {
    submit(leaveSchool) {
        return axios
            .post(API_URL + '/submit', {
                username: leaveSchool.username,
                studentId: leaveSchool.studentId,
                email: leaveSchool.email,
                department: leaveSchool.department,

                leaveDate: leaveSchool.leaveDate,
                enterDate: leaveSchool.enterDate,
                campus: leaveSchool.campus,
                gate: leaveSchool.gate,
                applicationReason: leaveSchool.applicationReason,
                travelTrajectory: leaveSchool.travelTrajectory,
                transportation: leaveSchool.transportation,
                applicationType: leaveSchool.applicationType,
                submitDate: leaveSchool.submitDate,
                status: 0
            })
    }
    getAll() {
        return axios
            .get(API_URL)
            .then(response => {
                return response;
            })
    }
    getAllByStudentId(studentId) {
        return axios
            .get(API_URL + '/' + studentId, {
                studentId: studentId
            })
            .then(response => {
                return response;
            })
    }
    changeStatus(leaveSchool, status) {
        return axios
            .put(API_URL + "/" + leaveSchool.studentId + "?submitDate=" + leaveSchool.submitDate.replace(" ", "T") + "&status=" + status)
            .then(response => {
                return response;
            })
    }
}

export default new LeaveSchoolService();
