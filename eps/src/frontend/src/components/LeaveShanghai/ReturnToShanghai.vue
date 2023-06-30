<template>
    <Form @submit="submit" :validation-schema="schema">

        <h3 class="header">返沪申请</h3>

        <!-- Return To Shanghai Date -->
        <div class="form-item">
            <label for="enterDate">返沪时间:</label>
            <Field name="enterDate" id="enterDate" class="left-label" type="datetime-local" style="margin-left: 26px;"/>
            <ErrorMessage name="enterDate" class="error-feedback"/>
        </div>

        <!-- Departure Province -->
        <div class="form-item">
            <label for="province">出发地省份:</label>
            <select name="province" class="left-label" id="province" style="width: 100px;">
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Departure City -->
        <div class="form-item">
            <label for="city">出发地城市:</label>
            <select name="city" class="left-label" id="city" style="width: 100px;">
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Application Reason -->
        <div class="form-item">
            <label for="applicationReason">申请理由:</label>
            <textarea name="applicationReason" id="applicationReason" class="left-label" type="text" placeholder="请输入申请理由" style="margin-left: 26px;"></textarea>
            <!-- <Field name="applicationReason" id="applicationReason" class="left-label" type="text"/> -->
        </div>

        <!-- Travel Trajectory -->
        <div class="form-item">
            <label for="travelTrajectory">出行轨迹:</label>
            <textarea name="travelTrajectory" id="travelTrajectory" class="left-label" type="text" placeholder="请输入出行轨迹" style="margin-left: 26px;"></textarea>
            <!-- <Field name="travelTrajectory" id="travelTrajectory" class="left-label" type="text"/> -->
        </div>

        <!-- Transportation -->
        <div class="form-item">
            <label for="transportation">交通工具: </label>
            <select name="transportation" class="left-label" id="transportation" style="width: 100px; margin-left: 26px;">
                <option value="" disabled selected hidden>请选择</option>
                <option value="car">汽车</option>
                <option value="bus">公交车</option>
                <option value="airplane">飞机</option>
                <option value="train">火车</option>
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
        enterDate: yup.string().required("ⓘ 该输入项为必填项"),
        promise: yup.string().required("ⓘ 该输入项为必填项")
    });
</script>

<script>
    import LeaveShanghaiService from "../../services/leave-shanghai.service";
    import DailyReportScript from "../../scripts/DailyReportScript";

    export default {
        mounted() {
            var provinceSel = document.getElementById("province");
            var citySel = document.getElementById("city");
            // Add province list to the options element
            for (var x in DailyReportScript.provinceObject) {
                if (x != "上海")
                    provinceSel.options[provinceSel.options.length] = new Option(x, x);
            }
            // Province value is changed
            provinceSel.onchange = function() {
                citySel.length = 1;
                //display correct values
                for (var y in DailyReportScript.provinceObject[this.value]) {
                    citySel.options[citySel.options.length] = new Option(y, y);
                }
            }
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        methods: {
            submit(returnToShanghai) {
                returnToShanghai.username = this.currentUser.username;
                returnToShanghai.studentId = this.currentUser.studentId;
                returnToShanghai.email = this.currentUser.email;
                returnToShanghai.department = this.currentUser.department;
                returnToShanghai.applicationType = "return";

                returnToShanghai.province = document.getElementById("province").value;
                returnToShanghai.province = document.getElementById("province").value;
                returnToShanghai.city = document.getElementById("city").value;
                returnToShanghai.applicationReason = document.getElementById("applicationReason").value;
                returnToShanghai.travelTrajectory = document.getElementById("travelTrajectory").value;
                returnToShanghai.transportation = document.getElementById("transportation").value;

                var tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds
                var localISOTime = (new Date(Date.now() - tzoffset)).toISOString().slice(0, -1);
                returnToShanghai.submitDate = localISOTime.substring(0, 19);

                console.log(returnToShanghai);

                LeaveShanghaiService.submit(returnToShanghai);
                alert("提交成功");
                this.$router.push( { path: "/leave-shanghai-application" } );
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
