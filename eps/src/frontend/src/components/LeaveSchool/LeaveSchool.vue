<template>
    <Form @submit="submit" :validation-schema="schema">
        
        <h3 class="header">出校申请</h3>

        <!-- Leave School Date -->
        <div class="form-item">
            <label for="leaveDate">出校时间:</label>
            <Field name="leaveDate" class="left-label" id="leaveDate" type="datetime-local"/>
            <ErrorMessage name="leaveDate" class="error-feedback"/>
        </div>

        <!-- Enter School Date -->
        <div class="form-item">
            <label for="enterDate">进校时间:</label>
            <Field name="enterDate" id="enterDate" class="left-label" type="datetime-local"/>
            <ErrorMessage name="enterDate" class="error-feedback"/>
        </div>

        <!-- Campus -->
        <div class="form-item">
            <label for="campus">校区:</label>
            <select name="campus" class="left-label" id="campus" style="width: 100px; margin-left: 42px;">
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Gate -->
        <div class="form-item">
            <label for="gate">校门:</label>
            <select name="gate" class="left-label" id="gate" style="width: 100px; margin-left: 42px;">
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Application Reason -->
        <div class="form-item">
            <label for="applicationReason">申请理由:</label>
            <textarea name="applicationReason" id="applicationReason" class="left-label" type="text" placeholder="请输入申请理由"></textarea>
            <!-- <Field name="applicationReason" id="applicationReason" class="left-label" type="text"/> -->
            <ErrorMessage name="applicationReason" class="error-feedback"/>
        </div>
        
        <!-- Travel Trajectory -->
        <div class="form-item">
            <label for="travelTrajectory">出行轨迹:</label>
            <textarea name="travelTrajectory" id="travelTrajectory" class="left-label" type="text" placeholder="请输入出行轨迹"></textarea>
            <!-- <Field name="travelTrajectory" id="travelTrajectory" class="left-label" type="text"/> -->
        </div>

        <!-- Transportation -->
        <div class="form-item">
            <label for="transportation">交通工具: </label>
            <select name="transportation" class="left-label" id="transportation" style="width: 100px;">
                <option value="" disabled selected hidden>请选择</option>
                <option value="car">汽车</option>
                <option value="bicycle">自行车</option>
                <option value="scooter">摩托车</option>
                <option value="bus">公交车</option>
                <option value="taxi">出租车</option>
                <option value="metro">地铁</option>
            </select>
        </div>

        <!-- Promise checkbox -->
        <div class="form-item">
            <Field name="promise" type="checkbox" id="promise" :value="false"/>
            <label for="promise" style="color: red !important; margin-left: 5px;">本人承诺以上情况属实，按照防控要求做好防护</label>
            <ErrorMessage name="promise" class="error-feedback"/>
        </div>

        <div class="button-container">
            <button class="submit-button">提交</button>
        </div>
    </Form>
</template>

<!-- Script setup for Vee-validate with yup -->
<script setup>
    import { Form, Field, ErrorMessage } from "vee-validate";
    import * as yup from "yup";

    const schema = yup.object({
        leaveDate: yup.string().required("ⓘ 该输入项为必填项"),
        enterDate: yup.string().required("ⓘ 该输入项为必填项"),
        promise: yup.string().required("ⓘ 该输入项为必填项")
    });
</script>

<script>
    import LeaveSchoolService from "../../services/leave-school.service";

    export default {
        mounted() {
            var campusObject = {
                "宝山": {
                    "北门": [],
                    "南门": [],
                    "东区1号门": [],
                    "新世纪北门": [],
                    "南区门": [],
                    "南二门": [],
                    "东门": [],
                    "东区二号门": [],
                    "东2门": [],
                    "西门": []
                },
                "延长": {
                    "北门": [],
                    "南门": [],
                    "西门": [],
                    "西部门": [],
                    "广中公寓门": []
                },
                "嘉定": {
                    "东门": [],
                    "北门": []
                }
            }
            var campusSel = document.getElementById("campus");
            var gateSel = document.getElementById("gate");
            // Add campus list to the options element
            for (var x in campusObject) {
                campusSel.options[campusSel.options.length] = new Option(x, x);
            }
            // Campus value is changed
            campusSel.onchange = function() {
                gateSel.length = 1;
                // Add gate list to the options element
                for (var y in campusObject[this.value]) {
                    gateSel.options[gateSel.options.length] = new Option(y, y);
                }
            }
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        methods: {
            submit(leaveSchool) {
                leaveSchool.username = this.currentUser.username;
                leaveSchool.studentId = this.currentUser.studentId;
                leaveSchool.email = this.currentUser.email;
                leaveSchool.department = this.currentUser.department;

                leaveSchool.applicationType = "leave";
                leaveSchool.campus = document.getElementById("campus").value;
                leaveSchool.gate = document.getElementById("gate").value;
                leaveSchool.applicationReason = document.getElementById("applicationReason").value;
                leaveSchool.travelTrajectory = document.getElementById("travelTrajectory").value;
                leaveSchool.transportation = document.getElementById("transportation").value;

                var tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds
                var localISOTime = (new Date(Date.now() - tzoffset)).toISOString().slice(0, -1);
                leaveSchool.submitDate = localISOTime.substring(0, 19);

                console.log(leaveSchool);

                LeaveSchoolService.submit(leaveSchool);
                alert("提交成功");
                this.$router.push( { path: "/leave-school-application" } );
            }
        }
    }
</script>

<style scoped>
    *{
        color: #1A284F;
    }
    .header{
        margin: 20px 0px 20px;
        padding-left: 10px;
        border-left: #1A284F solid 3.5px;
        
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
