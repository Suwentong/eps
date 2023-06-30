import axios from 'axios';

const API_URL = '/api/v1/visitor-review';

class VisitorReviewService {
    submit(visitorReview) {
        return axios
            .post(API_URL + '/submit', {
                username: visitorReview.username,
                studentId: visitorReview.studentId,
                email: visitorReview.email,
                department: visitorReview.department,

                visitorName: visitorReview.visitorName,
                idNumber: visitorReview.idNumber,
                phoneNumber: visitorReview.phoneNumber,
                campus: visitorReview.campus,
                gate: visitorReview.gate,
                startDate: visitorReview.startDate,
                endDate: visitorReview.endDate,

                submitDate: visitorReview.submitDate,
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
            .get(API_URL + "/" +studentId, {
                studentId: studentId
            })
            .then(response => {
                return response;
            })
    }
    changeStatus(visitorReview, status) {
        return axios
            .put(API_URL + "/" + visitorReview.studentId + "?submitDate=" + visitorReview.submitDate.replace(" ", "T") + "&status=" + status)
            .then(response => {
                return response;
            })
    }
}

export default new VisitorReviewService();
