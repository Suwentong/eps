<template>
    <Form @submit="submit" :validation-schema="schema">
        <h3 class="header">访客申请</h3>

        <!-- Visitor Name -->
        <div class="form-item">
            <label for="visitorName">访客姓名:</label>
            <Field name="visitorName" id="visitorName" type="text" style="margin-left: 42px;"/>
            <ErrorMessage name="visitorName" class="error-feedback"/>
        </div>

        <!-- ID Number -->
        <div class="form-item">
            <label for="idNumber">证件号码:</label>
            <Field name="idNumber" id="idNumber" type="text" style="margin-left: 42px;"/>
            <ErrorMessage name="idNumber" class="error-feedback"/>
        </div>

        <!-- Phone Number -->
        <div class="form-item">
            <label for="phoneNumber">访客电话:</label>
            <Field name="phoneNumber" id="phoneNumber" type="text" style="margin-left: 42px;"/>
            <ErrorMessage name="phoneNumber" class="error-feedback"/>
        </div>

         <!-- Campus -->
        <div class="form-item">
            <label for="campus">到访校区:</label>
            <select name="campus" id="campus" style="width: 100px; margin-left: 42px;">
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Gate -->
        <div class="form-item">
            <label for="gate">到访校门:</label>
            <select name="gate" id="gate" style="width: 100px; margin-left: 42px;">
                <option value="" disabled selected hidden>请选择</option>
            </select>
        </div>

        <!-- Visit Start Date -->
        <div class="form-item">
            <label for="startDate">访问开始时间:</label>
            <Field name="startDate" class="left-label" id="startDate" type="datetime-local"/>
            <ErrorMessage name="startDate" class="error-feedback"/>
        </div>

        <!-- Visit End Date -->
        <div class="form-item">
            <label for="endDate">访问结束时间:</label>
            <Field name="endDate" id="endDate" class="left-label" type="datetime-local"/>
            <ErrorMessage name="endDate" class="error-feedback"/>
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
        // leaveDate: yup.string().required("ⓘ 该输入项为必填项")
    });
</script>

<script>
    import VisitorReviewService from "../../services/visitor-review.service";

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
            submit(visitorReview) {
                visitorReview.username = this.currentUser.username;
                visitorReview.studentId = this.currentUser.studentId;
                visitorReview.email = this.currentUser.email;
                visitorReview.department = this.currentUser.department;

                visitorReview.visitorName = document.getElementById("visitorName").value;
                visitorReview.idNumber = document.getElementById("idNumber").value;
                visitorReview.phoneNumber = document.getElementById("phoneNumber").value;
                visitorReview.campus = document.getElementById("campus").value;
                visitorReview.gate = document.getElementById("gate").value;
                visitorReview.startDate = document.getElementById("startDate").value;
                visitorReview.endDate = document.getElementById("endDate").value;

                var tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds
                var localISOTime = (new Date(Date.now() - tzoffset)).toISOString().slice(0, -1);
                visitorReview.submitDate = localISOTime.substring(0, 19);
                
                VisitorReviewService.submit(visitorReview);
                alert("提交成功");
                this.$router.push( { path: "/visitor-review" } );
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
