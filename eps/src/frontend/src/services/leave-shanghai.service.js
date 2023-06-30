import axios from 'axios';

const API_URL = '/api/v1/leave-shanghai';

class LeaveShanghaiService {
    submit(leaveShanghai) {
        return axios
            .post(API_URL + '/submit', {
                username: leaveShanghai.username,
                studentId: leaveShanghai.studentId,
                email: leaveShanghai.email,
                department: leaveShanghai.department,
                applicationType: leaveShanghai.applicationType,

                leaveDate: leaveShanghai.leaveDate,
                enterDate: leaveShanghai.enterDate,
                province: leaveShanghai.province,
                city: leaveShanghai.city,
                destinationAddress: leaveShanghai.destinationAddress,
                applicationReason: leaveShanghai.applicationReason,
                travelTrajectory: leaveShanghai.travelTrajectory,
                transportation: leaveShanghai.transportation,
                submitDate: leaveShanghai.submitDate,
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
    changeStatus(leaveShanghai, status) {
        return axios
            .put(API_URL + "/" +leaveShanghai.studentId + "?submitDate=" + leaveShanghai.submitDate.replace(" ", "T") + "&status=" + status)
            .then(response => {
                return response;
            })
    }
}

export default new LeaveShanghaiService();
