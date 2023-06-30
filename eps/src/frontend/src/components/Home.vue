<template>
    <div>
        <h4 class="header">填报情况</h4>
        <div class="container">
            <strong id="submited">已提交:</strong>
            <label for="submited">{{ this.submited }}</label>

            <strong id="notSubmited">未提交:</strong>
            <label for="notSubmited">{{ this.notSubmited }}</label>
        </div>

        <strong class="notSubmited" v-if="this.submited < 30">✖ 填报记录不完整, 请尽快补报</strong>
        <strong class="submited" v-else>✔ 已全部填报</strong>

        <h4 class="header">最后一次进出校门申请的状态</h4>

        <div class="container">
            <strong id="leaveSchoolSubmitDate">提交日期:</strong>
            <label for="leaveSchoolSubmitDate">{{ this.lastLeaveSchoolApplication.submitDate }}</label>
            
            <strong id="leaveSchoolApplicationType">操作:</strong>
            <label for="leaveSchoolApplicationType" v-if="this.lastLeaveSchoolApplication.applicationType == 'leave'">出校申请</label>
            <label for="leaveSchoolApplicationType" v-else-if="this.lastLeaveSchoolApplication.applicationType == 'enter'">进校申请</label>
            
            <strong id="leaveSchoolStatus">状态:</strong>
            <label for="leaveSchoolStatus" v-if="this.lastLeaveSchoolApplication.status == 0" style="color: #f5bd05 !important;">审核中</label>
            <label for="leaveSchoolStatus" v-else-if="this.lastLeaveSchoolApplication.status == 1" style="color: #08a10b !important;">同意</label>
            <label for="leaveSchoolStatus" v-else-if="this.lastLeaveSchoolApplication.status == 2" style="color: #b00707 !important;">拒绝</label>
        </div>


        <!-- <table class="content-table table table-striped">
            <thead style="text-align: center;">
                <th>提交日期</th>
                <th>操作</th>
                <th>状态</th>
            </thead>
            <tbody v-if="leaveSchoolArray.length == 0">
                <td colspan="3" style="text-align: center;">暂无数据</td>
            </tbody>
            <tbody v-else style="text-align: center;">
                <td>{{ this.lastLeaveSchoolApplication.submitDate }}</td>
                <td v-if="this.lastLeaveSchoolApplication.applicationType == 'leave'">出校申请</td>
                <td v-if="this.lastLeaveSchoolApplication.applicationType == 'enter'">进校申请</td>
                <td v-if="this.lastLeaveSchoolApplication.status == 0" style="color: #f5bd05 !important;">审核中</td>
                <td v-if="this.lastLeaveSchoolApplication.status == 1" style="color: #08a10b !important;">同意</td>
                <td v-if="this.lastLeaveSchoolApplication.status == 2" style="color: #b00707 !important;">拒绝</td>
            </tbody>
        </table> -->
        <h4 class="header">最后一次返沪离沪申请的状态</h4>
        <div class="container">
            <strong id="leaveShanghaiSubmitDate">提交日期:</strong>
            <label for="leaveShanghaiSubmitDate">{{ this.lastLeaveShanghaiApplication.submitDate }}</label>
            
            <strong id="leaveShanghaiApplicationType">操作:</strong>
            <label for="leaveShanghaiApplicationType" v-if="this.lastLeaveShanghaiApplication.applicationType == 'leave'">离沪申请</label>
            <label for="leaveShanghaiApplicationType" v-else-if="this.lastLeaveShanghaiApplication.applicationType == 'return'">返沪申请</label>
            
            <strong id="leaveShanghaiStatus">状态:</strong>
            <label for="leaveShanghaiStatus" v-if="this.lastLeaveShanghaiApplication.status == 0" style="color: #f5bd05 !important;">审核中</label>
            <label for="leaveShanghaiStatus" v-else-if="this.lastLeaveShanghaiApplication.status == 1" style="color: #08a10b !important;">同意</label>
            <label for="leaveShanghaiStatus" v-else-if="this.lastLeaveShanghaiApplication.status == 2" style="color: #b00707 !important;">拒绝</label>
        </div>
        <h4 class="header">最后一次访客申请的状态</h4>
        <div class="container">
            <strong id="visitorReviewSubmitDate">提交日期:</strong>
            <label for="visitorReviewSubmitDate">{{ this.lastLeaveShanghaiApplication.submitDate }}</label>
            
            <strong id="visitorReviewVisitorName">访客姓名:</strong>
            <label for="visitorReviewVisitorName">{{ this.lastVisitorReviewApplication.visitorName }}</label>
            
            <strong id="visitorReviewStatus">状态:</strong>
            <label for="visitorReviewStatus" v-if="this.lastVisitorReviewApplication.status == 0" style="color: #f5bd05 !important;">审核中</label>
            <label for="visitorReviewStatus" v-else-if="this.lastVisitorReviewApplication.status == 1" style="color: #08a10b !important;">同意</label>
            <label for="visitorReviewStatus" v-else-if="this.lastVisitorReviewApplication.status == 2" style="color: #b00707 !important;">拒绝</label>
        </div>        
    </div>
</template>
  
<script>
    // import UserService from "../services/user.service";
    import DailyReportService from "../services/daily-report.service";
    import LeaveSchoolService from "../services/leave-school.service";
    import LeaveShanghaiService from "../services/leave-shanghai.service";
    import VisitorReviewService from "../services/visitor-review.service";


    export default {
        name: "Home",
        data() {
            return {
                // content: "",
                dailyReportArray: [],
                leaveSchoolArray: [],
                leaveShanghaiArray: [],
                visitorReviewArray: [],
                lastLeaveSchoolApplication: {},
                lastLeaveShanghaiApplication: {},
                lastVisitorReviewApplication: {},
                submited: 0,
                notSubmited: 0
            };
        },
        mounted() {
          // UserService.getPublicContent().then(
          //   (response) => {
          //     this.content = response.data;
          //   },
          //   (error) => {
          //     this.content =
          //       (error.response &&
          //         error.response.data &&
          //         error.response.data.message) ||
          //       error.message ||
          //       error.toString();
          //   }
          // );
          DailyReportService.getAllByStudentId(this.currentUser.studentId).then(
                (response) => {
                    this.dailyReportArray = response.data;
                    this.submited = this.dailyReportArray.length;
                    this.notSubmited = 30 - this.submited;
                }

          )
          LeaveSchoolService.getAllByStudentId(this.currentUser.studentId).then(
                (response) => {
                    this.leaveSchoolArray = response.data;
                    if (this.leaveSchoolArray.length > 0) {
                        this.lastLeaveSchoolApplication = this.leaveSchoolArray[this.leaveSchoolArray.length - 1];
                        this.lastLeaveSchoolApplication.submitDate = this.lastLeaveSchoolApplication.submitDate.substring(0, 19).replace("T", " ");

                    }
                    else
                        this.lastLeaveSchoolApplication = null;
                }
            )

            LeaveShanghaiService.getAllByStudentId(this.currentUser.studentId).then(
                (response) => {
                    this.leaveShanghaiArray = response.data;
                    if (this.leaveShanghaiArray.length > 0) {
                        this.lastLeaveShanghaiApplication = this.leaveShanghaiArray[this.leaveShanghaiArray.length - 1];
                        this.lastLeaveShanghaiApplication.submitDate = this.lastLeaveShanghaiApplication.submitDate.substring(0, 19).replace("T", " ");

                    }
                    else
                        this.lastLeaveShanghaiApplication = null;
                }
            )

            VisitorReviewService.getAllByStudentId(this.currentUser.studentId).then(
                (response) => {
                    this.visitorReviewArray = response.data;
                    if (this.visitorReviewArray.length > 0) {
                        this.lastVisitorReviewApplication = this.visitorReviewArray[this.visitorReviewArray.length - 1];
                        this.lastVisitorReviewApplication.submitDate = this.lastVisitorReviewApplication.submitDate.substring(0, 19).replace("T", " ");

                    }
                    else
                        this.lastVisitorReviewApplication = null;
                }
            )
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
        },
    };
</script>

<style scoped>
    .header{
        margin: 20px 0px 10px;
        padding-left: 10px;
        border-left: #1A284F solid 3.5px;
    }
    .submited {
        font-size: 18px; 
        color: #08a10b;
    }
    .notSubmited {
        margin: 17px;
        font-size: 18px; 
        color: #b00707;
    }
    .container{
        display: grid;
        height: 150px;
        align-content: space-evenly;
        grid-template-columns: 150px 200px;
        font-size: 18px;
    }
</style>