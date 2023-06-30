import DailyReportService from '../services/daily-report.service';

export const dailyrep = {
    namespaced: true,
    actions: {
        submit( { commit }, dailyreport) {
            console.log("Module.js:" + dailyreport);
            return DailyReportService.submit(dailyreport).then(
                dailyreport => {
                    commit('submitSuccess', dailyreport);
                    return Promise.resolve(dailyreport);
                },
                error => {
                    commit('submitFailure');
                    return Promise.reject(error);
                }
            );
        },
        getAll() {
            return DailyReportService.getAll().then(
                (response) => {
                    return Promise.resolve(response);
                }
            )
        },
        getAllByStudentId(studentId) {
            return DailyReportService.getAllByStudentId(studentId).then(
                (response) => {
                    return Promise.resolve(response);
                }
            )
        },
        getByDate(studentId, date) {
            console.log(studentId)
            console.log(date)
            return DailyReportService.getByDate(studentId, date).then(
                (response) => {
                    return Promise.resolve(response);
                }
            )
        },
        test() {
            console.log("OK");
        }
    },
    mutations: {
        submitSuccess(state) {
            state.status.submited = true;
            // state.dailyreport = dailyreport;
        },
        submitFailure(state) {
            state.status.submited = false;
        }
    }
};