<template>
    <div style="display: inline-block; margin-left: 120px; width: 100%;">
        <h3 class="header">用户信息</h3>
        
        <div class="form-item">
            <label for="studentId">学工号:</label>
            <a style="margin-left: 26px;">{{ leaveShanghai.studentId }}</a>
        </div>
        
        <div class="form-item">
            <label for="username">姓名:</label>
            <a style="margin-left: 42px;">{{ leaveShanghai.username }}</a>
        </div>
        
        <div class="form-item">
            <label for="department">学院:</label>
            <a style="margin-left: 42px;">{{ leaveShanghai.department }}</a>
        </div>

        <h3 class="header">申请信息</h3>

        <div class="form-item">
            <label for="username">申请类型:</label>
            <a style="margin-left: 58px;" v-if="leaveShanghai.applicationType == 'leave'">离沪申请</a>
            <a style="margin-left: 58px;" v-if="leaveShanghai.applicationType == 'return'">返沪申请</a>
        </div>

        <!-- Leave Shanghai Date -->
        <div class="form-item" id="leaveDate-div">
            <label for="leaveDate">离沪时间:</label>
            <input style="margin-left: 58px;" name="leaveDate" class="left-label" id="leaveDate" type="datetime-local" disabled/>
        </div>

        <!-- Enter Shanghai Date -->
        <div class="form-item">
            <label for="enterDate">返沪时间:</label>
            <input style="margin-left: 58px;" name="enterDate" id="enterDate" class="left-label" type="datetime-local" disabled/>
        </div>

        <!-- Destination Province -->
        <div class="form-item">
            <label for="province">目的地省份:</label>
            <select name="province" class="left-label" id="province" style="width: 100px; margin-left: 42px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Destination City -->
        <div class="form-item">
            <label for="city">目的地城市:</label>
            <select name="city" class="left-label" id="city" style="width: 100px; margin-left: 42px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Destination Address -->
        <div class="form-item" id="destinationAddress-div">
            <label for="destinationAddress">目的地详细地址:</label>
            <input name="destinationAddress" class="left-label" id="destinationAddress" type="text" disabled/>
        </div>

        <!-- Application Reason -->
        <div class="form-item">
            <label for="applicationReason">申请理由:</label>
            <textarea style="margin-left: 58px;" name="applicationReason" id="applicationReason" class="left-label" type="text" disabled></textarea>
        </div>
        
        <!-- Travel Trajectory -->
        <div class="form-item">
            <label for="travelTrajectory">出行轨迹:</label>
            <textarea style="margin-left: 58px;" name="travelTrajectory" id="travelTrajectory" class="left-label" type="text" disabled></textarea>
        </div>

        <!-- Transportation -->
        <div class="form-item">
            <label for="transportation">交通工具: </label>
            <select name="transportation" class="left-label" id="transportation" style="width: 100px; margin-left: 58px;" disabled>
                <option value="" disabled selected hidden>请选择</option>
                <option value="car">汽车</option>
                <option value="bus">公交车</option>
                <option value="airplane">飞机</option>
                <option value="train">火车</option>
            </select>
        </div>

        <div class="form-item" v-if="this.leaveShanghai.status != 0">
            <label for="status">状态:</label>
            <a v-if="this.leaveShanghai.status == 1" style="margin-left: 90px; color: #08a10b !important;">同意</a>
            <a v-if="this.leaveShanghai.status == 2" style="margin-left: 90px; color: #b00707 !important;">拒绝</a>
        </div>

        <div class="button-container" v-if="this.leaveShanghai.status == 0">
            <button class="submit-button" @click="changeStatus(this.leaveShanghai, 1)">同意</button>
            <button class="submit-button" @click="changeStatus(this.leaveShanghai, 2)">拒绝</button>
        </div>
        <div class="button-container" v-else>
            <button class="submit-button" @click="returnBack">返回</button>
        </div>
    </div>
</template>

<script>
    import LeaveShanghaiService from "../../services/leave-shanghai.service";

    export default {
        data() {
            return {
                leaveShanghaiArray: [],
                leaveShanghai: {}
            };
        },
        mounted() {
            var provinceSel = document.getElementById("province");
            var citySel = document.getElementById("city");

            LeaveShanghaiService.getAll().then(
                (response) => {
                    this.leaveShanghaiArray = response.data;
                    for (let index = 0; index < this.leaveShanghaiArray.length; index++) {
                        this.leaveShanghaiArray[index].submitDate = this.leaveShanghaiArray[index].submitDate.substring(0, 19).replace("T", " ");
                        if (this.leaveShanghaiArray[index].submitDate == localStorage.submitDate && this.leaveShanghaiArray[index].studentId == localStorage.studentId) {
                            this.leaveShanghai = this.leaveShanghaiArray[index];
                            if (this.leaveShanghai.applicationType == "leave")
                                document.getElementById("leaveDate").value = this.leaveShanghai.leaveDate;
                            else {
                                document.getElementById("leaveDate-div").style.display = "none";
                                document.getElementById("destinationAddress-div").style.display = "none";
                            }
                            
                            document.getElementById("enterDate").value = this.leaveShanghai.enterDate;
                            document.getElementById("applicationReason").value = this.leaveShanghai.applicationReason;
                            document.getElementById("travelTrajectory").value = this.leaveShanghai.travelTrajectory;
                            document.getElementById("transportation").value = this.leaveShanghai.transportation;
                            document.getElementById("destinationAddress").value = this.leaveShanghai.destinationAddress;
                            provinceSel.options[provinceSel.options.length] = new Option(this.leaveShanghai.province, this.leaveShanghai.province);
                            provinceSel.value = this.leaveShanghai.province;
                            citySel.options[citySel.options.length] = new Option(this.leaveShanghai.city, this.leaveShanghai.city);
                            citySel.value = this.leaveShanghai.city;
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
            changeStatus(leaveShanghai, status) {
                LeaveShanghaiService.changeStatus(leaveShanghai, status);
                alert("提交成功");
                this.$router.push( { path: "/leave-shanghai-admin-application-list" } );
            },
            returnBack() {
                scroll(0,0);
                this.$router.push( { path: "/leave-shanghai-admin-application-list" } );
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
