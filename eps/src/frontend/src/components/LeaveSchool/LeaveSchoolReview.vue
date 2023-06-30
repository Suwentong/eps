<template>
    <div style="display: inline-block; margin-left: 120px; width: 100%;">
        <h3 class="header">用户信息</h3>
        
        <div class="form-item">
            <label for="studentId">学工号:</label>
            <a style="margin-left: 26px;">{{ leaveSchool.studentId }}</a>
        </div>
        
        <div class="form-item">
            <label for="username">姓名:</label>
            <a style="margin-left: 42px;">{{ leaveSchool.username }}</a>
        </div>
        
        <div class="form-item">
            <label for="department">学院:</label>
            <a style="margin-left: 42px;">{{ leaveSchool.department }}</a>
        </div>

        <h3 class="header">申请信息</h3>

        <div class="form-item">
            <label for="username">申请类型:</label>
            <a style="margin-left: 10px;" v-if="leaveSchool.applicationType == 'leave'">出校申请</a>
            <a style="margin-left: 10px;" v-if="leaveSchool.applicationType == 'enter'">进校申请</a>
        </div>

        <!-- Leave School Date -->
        <div class="form-item" id="leaveDate-div">
            <label for="leaveDate">出校时间:</label>
            <input name="leaveDate" class="left-label" id="leaveDate" type="datetime-local" disabled/>
        </div>

        <!-- Enter School Date -->
        <div class="form-item">
            <label for="enterDate">进校时间:</label>
            <input name="enterDate" id="enterDate" class="left-label" type="datetime-local" disabled/>
        </div>

        <!-- Campus -->
        <div class="form-item">
            <label for="campus">校区:</label>
            <select name="campus" class="left-label" id="campus" style="width: 100px; margin-left: 42px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Gate -->
        <div class="form-item">
            <label for="gate">校门:</label>
            <select name="gate" class="left-label" id="gate" style="width: 100px; margin-left: 42px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Application Reason -->
        <div class="form-item">
            <label for="applicationReason">申请理由:</label>
            <textarea name="applicationReason" id="applicationReason" class="left-label" type="text" disabled></textarea>
        </div>
        
        <!-- Travel Trajectory -->
        <div class="form-item">
            <label for="travelTrajectory">出行轨迹:</label>
            <textarea name="travelTrajectory" id="travelTrajectory" class="left-label" type="text" disabled></textarea>
        </div>

        <!-- Transportation -->
        <div class="form-item">
            <label for="transportation">交通工具: </label>
            <select name="transportation" class="left-label" id="transportation" style="width: 100px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
                <option value="car">汽车</option>
                <option value="bicycle">自行车</option>
                <option value="scooter">摩托车</option>
                <option value="bus">公交车</option>
                <option value="taxi">出租车</option>
                <option value="metro">地铁</option>
            </select>
        </div>

        <div class="form-item" v-if="this.leaveSchool.status != 0">
            <label for="status">状态:</label>
            <a v-if="this.leaveSchool.status == 1" style="margin-left: 42px; color: #08a10b !important;">同意</a>
            <a v-if="this.leaveSchool.status == 2" style="margin-left: 42px; color: #b00707 !important;">拒绝</a>
        </div>

        <div class="button-container" v-if="this.leaveSchool.status == 0">
            <button class="submit-button" @click="changeStatus(this.leaveSchool, 1)">同意</button>
            <button class="submit-button" @click="changeStatus(this.leaveSchool, 2)">拒绝</button>
        </div>
        <div class="button-container" v-else>
            <button class="submit-button" @click="returnBack">返回</button>
        </div>
    </div>
</template>

<script>
    import LeaveSchoolService from "../../services/leave-school.service";

    export default {
        data() {
            return {
                leaveSchoolArray: [],
                leaveSchool: {}
            };
        },
        mounted() {
            var campusSel = document.getElementById("campus");
            var gateSel = document.getElementById("gate");

            LeaveSchoolService.getAll().then(
                (response) => {
                    this.leaveSchoolArray = response.data;
                    for (let index = 0; index < this.leaveSchoolArray.length; index++) {
                        this.leaveSchoolArray[index].submitDate = this.leaveSchoolArray[index].submitDate.substring(0, 19).replace("T", " ");
                        if (this.leaveSchoolArray[index].submitDate == localStorage.submitDate && this.leaveSchoolArray[index].studentId == localStorage.studentId) {
                            this.leaveSchool = this.leaveSchoolArray[index];
                            if (this.leaveSchool.applicationType == "leave")
                                document.getElementById("leaveDate").value = this.leaveSchool.leaveDate;
                            else
                                document.getElementById("leaveDate-div").style.display = "none";
                                
                            document.getElementById("enterDate").value = this.leaveSchool.enterDate;
                            document.getElementById("campus").value = this.leaveSchool.campus;
                            document.getElementById("gate").value = this.leaveSchool.gate;
                            document.getElementById("applicationReason").value = this.leaveSchool.applicationReason;
                            document.getElementById("travelTrajectory").value = this.leaveSchool.travelTrajectory;
                            document.getElementById("transportation").value = this.leaveSchool.transportation;
                            campusSel.options[campusSel.options.length] = new Option(this.leaveSchool.campus, this.leaveSchool.campus);
                            campusSel.value = this.leaveSchool.campus;
                            gateSel.options[gateSel.options.length] = new Option(this.leaveSchool.gate, this.leaveSchool.gate);
                            gateSel.value = this.leaveSchool.gate;
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
            changeStatus(leaveSchool, status) {
                LeaveSchoolService.changeStatus(leaveSchool, status);
                alert("提交成功");
                this.$router.push( { path: "/leave-school-admin-application-list" } );
            },
            returnBack() {
                scroll(0,0);
                this.$router.push( { path: "/leave-school-admin-application-list" } );
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
