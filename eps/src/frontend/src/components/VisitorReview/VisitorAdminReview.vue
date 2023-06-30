<template>
    <div style="display: inline-block; margin-left: 120px; width: 100%;">
        <h3 class="header">用户信息</h3>
        
        <div class="form-item">
            <label for="studentId">学工号:</label>
            <a style="margin-left: 26px;">{{ visitorReview.studentId }}</a>
        </div>
        
        <div class="form-item">
            <label for="username">姓名:</label>
            <a style="margin-left: 42px;">{{ visitorReview.username }}</a>
        </div>
        
        <div class="form-item">
            <label for="department">学院:</label>
            <a style="margin-left: 42px;">{{ visitorReview.department }}</a>
        </div>

        <h3 class="header">访客信息</h3>

        <!-- Visitor Name -->
        <div class="form-item">
            <label for="visitorName">访客姓名:</label>
            <input name="visitorName" id="visitorName" type="text" style="margin-left: 42px;" disabled/>
        </div>

        <!-- ID Number -->
        <div class="form-item">
            <label for="idNumber">证件号码:</label>
            <input name="idNumber" id="idNumber" type="text" style="margin-left: 42px;" disabled/>
        </div>

        <!-- Phone Number -->
        <div class="form-item">
            <label for="phoneNumber">访客电话:</label>
            <input name="phoneNumber" id="phoneNumber" type="text" style="margin-left: 42px;" disabled/>
        </div>

         <!-- Campus -->
        <div class="form-item">
            <label for="campus">到访校区:</label>
            <select name="campus" id="campus" style="width: 100px; margin-left: 42px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Gate -->
        <div class="form-item">
            <label for="gate">到访校门:</label>
            <select name="gate" id="gate" style="width: 100px; margin-left: 42px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Visit Start Date -->
        <div class="form-item">
            <label for="startDate">访问开始时间:</label>
            <input name="startDate" class="left-label" id="startDate" type="datetime-local" disabled/>
        </div>

        <!-- Visit End Date -->
        <div class="form-item">
            <label for="endDate">访问结束时间:</label>
            <input name="endDate" id="endDate" class="left-label" type="datetime-local" disabled/>
        </div>

        <div class="form-item" v-if="this.visitorReview.status != 0">
            <label for="status">状态:</label>
            <a v-if="this.visitorReview.status == 1" style="margin-left: 74px; color: #08a10b !important;">同意</a>
            <a v-if="this.visitorReview.status == 2" style="margin-left: 74px; color: #b00707 !important;">拒绝</a>
        </div>

        <div class="button-container" v-if="this.visitorReview.status == 0">
            <button class="submit-button" @click="changeStatus(this.visitorReview, 1)">同意</button>
            <button class="submit-button" @click="changeStatus(this.visitorReview, 2)">拒绝</button>
        </div>
        <div class="button-container" v-else>
            <button class="submit-button" @click="returnBack">返回</button>
        </div>
    </div>
</template>

<script>
    import VisitorReviewService from "../../services/visitor-review.service";

    export default {
        data() {
            return {
                visitorReviewArray: [],
                visitorReview: {}
            };
        },
        mounted() {
            var campusSel = document.getElementById("campus");
            var gateSel = document.getElementById("gate");

            VisitorReviewService.getAll().then(
                (response) => {
                    this.visitorReviewArray = response.data;
                    for (let index = 0; index < this.visitorReviewArray.length; index++) {
                        this.visitorReviewArray[index].submitDate = this.visitorReviewArray[index].submitDate.substring(0, 19).replace("T", " ");
                        if (this.visitorReviewArray[index].submitDate == localStorage.submitDate && this.visitorReviewArray[index].studentId == localStorage.studentId) {
                            this.visitorReview = this.visitorReviewArray[index];
                            // if (this.visitorReview.applicationType == "leave")
                            //     document.getElementById("leaveDate").value = this.visitorReview.leaveDate;
                            // else {
                            //     document.getElementById("leaveDate-div").style.display = "none";
                            //     document.getElementById("destinationAddress-div").style.display = "none";
                            // }
                            
                            document.getElementById("visitorName").value = this.visitorReview.visitorName;
                            document.getElementById("idNumber").value = this.visitorReview.idNumber;
                            document.getElementById("phoneNumber").value = this.visitorReview.phoneNumber;
                            document.getElementById("campus").value = this.visitorReview.campus;
                            document.getElementById("gate").value = this.visitorReview.gate;
                            document.getElementById("startDate").value = this.visitorReview.startDate;
                            document.getElementById("endDate").value = this.visitorReview.endDate;

                            campusSel.options[campusSel.options.length] = new Option(this.visitorReview.campus, this.visitorReview.campus);
                            campusSel.value = this.visitorReview.campus;
                            gateSel.options[gateSel.options.length] = new Option(this.visitorReview.gate, this.visitorReview.gate);
                            gateSel.value = this.visitorReview.gate;
                        }
                    }
                }
            )
            
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        methods: {
            changeStatus(visitorReview, status) {
                VisitorReviewService.changeStatus(visitorReview, status);
                alert("提交成功");
                this.$router.push( { path: "/visitor-review-admin" } );
            },
            returnBack() {
                scroll(0,0);
                this.$router.push( { path: "/visitor-review-admin" } );
            }
        }
    }
</script>

<style scoped>
    *{
        color: #1A284F;
    }
    .header{
        margin: 20px 0px 40px 20px;
        margin-left: -40px;
        padding-left: 10px;
        border-left: #1A284F solid 3.5px;
        color: #1A284F;
    }
    .error-feedback{
        padding-left: 50px;
        color: red;
    }
    .form-item {
        padding: 10px;
    }
    .left-label {
        margin-left: 10px;
    }
    .button-container{
        text-align: center;
    }
    .submit-button {
        color: white;
        margin: 5px;
        background-color: #1A284F;
        width: 80px;
        height: 40px;
        border-radius: 40px !important;
        border: none;
        outline: none;
        cursor: pointer;
        font-size: 1em;
        font-weight: 600;
    }
    #applicationReason, #travelTrajectory{
        height: 150px;
        width: 500px;
    }
</style>
