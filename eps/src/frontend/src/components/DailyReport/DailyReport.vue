<template>
    <Form @submit="submit" :validation-schema="schema">
        <!-- Promise checkbox -->
        <div class="form-item">
            <Field name="promise" type="checkbox" id="promise" :value="false"/>
            <label for="promise" style="color: red !important; margin-left: 5px;">我承诺，以下报送内容真实有效并可用于学校管理需要！</label>
            <ErrorMessage name="promise" class="error-feedback"/>
        </div>

        <!--Report Date-->
        <div class="form-item" id="reportDate-div">
            <label for="reportDate">报送日期: </label>
            <Field name="date" type="date" class="left-label" id="reportDate" style="text-align: center; width: 140px; padding-left: 12px;" v-model="date" disabled/>
        </div>

        <!-- Physical Condition -->
        <div class="form-item" id="physicalCondition-div">
            <label for="phyical-condition">当前身体状况: </label>
            <Field type="radio" class="left-label" id="good" name="physicalCondition" value="Good"/>
            <label for="good">良好 (体温不高于37.3)</label>
            <Field type="radio" class="left-label" id="discomfort" name="physicalCondition" value="Discomfort"/>
            <label for="discomfort">不适</label>
        </div>

        <!-- Symptoms -->
        <div class="form-item" id="symptoms-div">
            <label>症状:</label>
            <Field type="checkbox" class="left-label" name="symptoms" id="cold" value="Cold"/>
            <label for="cold">感冒</label>
            <Field type="checkbox" class="left-label" name="symptoms" id="cough" value="Cough"/>
            <label for="cough">咳嗽</label>
            <Field type="checkbox" class="left-label" name="symptoms" id="fever" value="Fever"/>
            <label for="fever">发热</label>
        </div>

        <!-- Temperature -->
        <div class="form-item" id="temperature-div">
            <label for="temperature">体温:</label>
            <Field type="text" class="left-label" name="temperature" id="temperature"/>
            <label for="temperature">℃</label>
        </div>
        
        <!-- In China or Not -->
        <div class="form-item" id="inChinaOrNot-div">
            <label for="in-china-or-not">当天是否在国内:</label>
            <Field type="radio" class="left-label" id="in-china" name="inChinaOrNot" value="InChina"/>
            <label for="in-china">国内</label>
            <Field type="radio" class="left-label" id="abroad" name="inChinaOrNot" value="Abroad"/>
            <label for="abroad">国外</label>
        </div>

        <!-- In Shanghai or Not -->
        <div class="form-item" id="inShanghaiOrNot-div">
            <label for="inShanghaiOrNot">当天是否在上海:</label>
            <ErrorMessage name="inShanghaiOrNot" class="error-feedback"/><br>
            <Field type="radio" id="in-school" name="inShanghaiOrNot" value="InSchool"/>
            <label for="in-school">在上海 (进学校)</label><br>
            <Field type="radio" id="outside-school" name="inShanghaiOrNot" value="OutsideSchool"/>
            <label for="outside-school">在上海 (不进学校)</label><br>
            <Field type="radio" id="not-in-shanghai" name="inShanghaiOrNot" value="NotInShanghai"/>
            <label for="not-in-shanghai">不在上海</label>
        </div>

        <!-- Campuses to Enter-->
        <div class="form-item" id="campusesToEnter-div">
            <label for="campusesToEnter">当天进校校区:</label>
            <Field type="checkbox" class="left-label" name="campusesToEnter" id="baoshan" value="Baoshan"/>
            <label for="baoshan">宝山</label>
            <Field type="checkbox" class="left-label" name="campusesToEnter" id="yanchang" value="Yanchang"/>
            <label for="yanchang">延长</label>
            <Field type="checkbox" class="left-label" name="campusesToEnter" id="jiading" value="Jiading"/>
            <label for="jiading">嘉定</label>
            <Field type="checkbox" class="left-label" name="campusesToEnter" id="xinzhalu" value="Xinzhalu"/>
            <label for="xinzhalu">新闸路</label>
            <ErrorMessage name="campusesToEnter" class="error-feedback"/><br>
        </div>

        <!-- Stay in Campus or Not -->
        <div class="form-item" id="stayInCampusOrNot-div">
            <label>当晚住校且当天不离校:</label>
            <Field type="radio" class="left-label" name="stayInCampusOrNot" id="stay-in-campus" value="StayInCampus"/>
            <label for="stay-in-campus">是</label>
            <Field type="radio" class="left-label" name="stayInCampusOrNot" id="stay-outside" value="StayOutside"/>
            <label for="stay-outside">否</label>
            <ErrorMessage name="stayInCampusOrNot" class="error-feedback"/><br>
        </div>

        <!-- Campus to Stay -->
        <div class="form-item" id="campusToStay-div">
            <label for="campusToStay">所在校区:</label>
            <Field type="radio" class="left-label" name="campusToStay" id="baoshan-radio" value="Baoshan"/>
            <label for="baoshan-radio">宝山</label>
            <Field type="radio" class="left-label" name="campusToStay" id="yanchang-radio" value="Yanchang"/>
            <label for="yanchang-radio">延长</label>
            <Field type="radio" class="left-label" name="campusToStay" id="jiading-radio" value="Jiading"/>
            <label for="jiading-radio">嘉定</label>
            <Field type="radio" class="left-label" name="campusToStay" id="xinzhalu-radio" value="Xinzhalu"/>
            <label for="xinzhalu-radio">新闸路</label>
            <ErrorMessage name="campusToStay" class="error-feedback"/><br>
        </div>

        <!-- Enter School or Not -->
        <div class="form-item" id="enterSchoolOrNot-div">
            <label for="enterSchoolOrNot">日报提交后是否需要申请明天的进出校: </label>
            <ErrorMessage name="enterSchoolOrNot" class="error-feedback"/><br>
            <Field type="radio" id="do-not-enter" name="enterSchoolOrNot" value="DoNotEnter"/>
            <label for="do-not-enter">不需要进出校, 不申请</label><br>
            <Field type="radio" id="enter-school" name="enterSchoolOrNot" value="EnterSchool"/>
            <label for="enter-school">需要申请进校</label><br>
            <Field type="radio" id="leave-school" name="enterSchoolOrNot" value="LeaveSchool"/>
            <label for="leave-school">需要申请出校</label>
        </div>

        <!-- Province -->
        <div class="form-item" id="province-div">
            <label for="province">当天所在省: </label>
            <select name="province" class="left-label" id="province" style="width: 100px; margin-left: 35px;">
                <option name="province" value="" selected="selected">选择省份</option>
            </select>
        </div>

        <!-- City -->
        <div class="form-item" id="city-div">
            <label for="city">当天所在市: </label>
            <select name="city" class="left-label" id="city" style="width: 100px; margin-left: 35px;">
                <option value="" selected="selected">选择市</option>
            </select>
        </div>

        <!-- County -->
        <div class="form-item" id="county-div">
            <label for="county">当天所在县区: </label>
            <select name="county" class="left-label" id="county" style="width: 100px; margin-left: 20px;">
                <option value="" selected="selected">选择县区</option>
            </select>
        </div>

        <!-- Country -->
        <div class="form-item" id="country-div">
            <label for="country">当天所在国家: </label>
            <select name="country" class="left-label" id="country" style="width: 100px; margin-left: 20px;">
                <option value="" selected="selected">选择国家</option>
            </select>
        </div>

        <!-- Address -->
        <div class="form-item" id="address-div">
            <label for="address" id="address-label">国内详细地址 (省市区县无需重复填写):</label><br>
            <Field type="text" name="address" id="address"/>
            <ErrorMessage name="address" class="error-feedback"/>
        </div>

        <!-- Is Home Address -->
        <div class="form-item" id="isHomeAddress-div">
            <label for="isHomeAddress">是否家庭地址: </label>
            <Field type="radio" class="left-label" id="home-address" name="isHomeAddress" value="HomeAddress"/>
            <label for="home-address">家庭地址</label>
            <Field type="radio" class="left-label" id="not-home-address" name="isHomeAddress" value="NotHomeAddress"/>
            <label for="not-home-address">不是家庭地址</label>
            <ErrorMessage name="isHomeAddress" class="error-feedback"/>
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
        promise: yup.string().required("ⓘ 该输入项为必填项"),
        inShanghaiOrNot: yup.string()
                            .when("inChinaOrNot", {
                                is: (v) => v === "InChina" || v == null,
                                then: () => yup.string().required("ⓘ 该输入项为必填项")
                            }),
        enterSchoolOrNot: yup.string()
                            .when("inChinaOrNot", {
                                is: (v) => v === "InChina" || v == null,
                                then: () => yup.string().required("ⓘ 该输入项为必填项")
                            }),
        // campusesToEnter: yup.string()
        //                     .when("inShanghaiOrNot", {
        //                         is: (v) => v === "InSchool" || v == null,
        //                         then: () => yup.string().required("ⓘ 该输入项为必填项")
        //                     }),
        // stayInCampusOrNot: yup.string()
        //                     .when("inShanghaiOrNot", {
        //                         is: (v) => v === "InSchool" || v == null,
        //                         then: () => yup.string().required("ⓘ 该输入项为必填项")
        //                     }),
        // campusToStay: yup.string()
        //                     .when("inShanghaiOrNot", {
        //                         is: (v) => v === "InSchool" || v == null,
        //                         then: () => yup.string().required("ⓘ 该输入项为必填项")
        //                     }),
        
        // ***
        
        // province: yup.string()
        //                     .when("inChinaOrNot", {
        //                         is: (v) => v === "InChina" || v == null,
        //                         then: () => yup.string().required("ⓘ 该输入项为必填项")
        //                     }),
        address: yup.string().required("ⓘ 该输入项为必填项"),
        isHomeAddress: yup.string().required("ⓘ 该输入项为必填项")
    });
</script>

<script>
    import DailyReportScript from "../../scripts/DailyReportScript";

    export default {
        name: "DailyReport",
        data() {
            return {
                date: localStorage.date
            };
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        mounted() {
            var provinceSel = document.getElementById("province");
            var citySel = document.getElementById("city");
            var countySel = document.getElementById("county");
            var countrySel = document.getElementById("country");
            
            // Symptoms, temperature
            var symptomsDiv = document.getElementById("symptoms-div");
            var temperatureDiv = document.getElementById("temperature-div");
            
            // Shanghai, campuses
            var inShanghaiOrNotDiv = document.getElementById("inShanghaiOrNot-div");
            var campusesToEnterDiv = document.getElementById("campusesToEnter-div");
            var stayInCampusOrNotDiv = document.getElementById("stayInCampusOrNot-div");
            var campusToStayDiv = document.getElementById("campusToStay-div");

            // Enter school or not
            var enterSchoolOrNotDiv = document.getElementById("enterSchoolOrNot-div");

            // Province, city, county, country
            var provinceDiv = document.getElementById("province-div");
            var cityDiv = document.getElementById("city-div");
            var countyDiv = document.getElementById("county-div");
            var countryDiv = document.getElementById("country-div");
            
            // Hiding unnecessary elements
            symptomsDiv.style.display = "none";
            temperatureDiv.style.display = "none";

            campusesToEnterDiv.style.display = "none";
            stayInCampusOrNotDiv.style.display = "none";
            campusToStayDiv.style.display = "none";
            
            cityDiv.style.display = "none";
            countyDiv.style.display = "none";
            countryDiv.style.display = "none";

            document.getElementById("good").checked = true;
            document.getElementById("in-china").checked = true;
            console.log(document.querySelector('input[name="physicalCondition"]').value)
            console.log(document.querySelector('input[name="inChinaOrNot"]').value)

            // Event listener to show/hide elements
            document.addEventListener('input',(e)=>{
                // Physical condition value is changed
                if(e.target.getAttribute('name')=="physicalCondition") {
                    if(e.target.value=="Discomfort") {
                        symptomsDiv.style.display = "block";
                        temperatureDiv.style.display = "block";
                    }
                    else if(e.target.value=="Good") {
                        symptomsDiv.style.display = "none";
                        temperatureDiv.style.display = "none";
                        document.getElementById("cold").checked = false;
                        document.getElementById("cough").checked = false;
                        document.getElementById("fever").checked = false;
                        document.getElementById("temperature").value = "";
                    }
                }
                // In China or not value is changed
                else if(e.target.getAttribute('name')=="inChinaOrNot") {
                    if(e.target.value=="Abroad") {
                        inShanghaiOrNotDiv.style.display = "none";
                        campusesToEnterDiv.style.display = "none";
                        stayInCampusOrNotDiv.style.display = "none";
                        campusToStayDiv.style.display = "none";
                        enterSchoolOrNotDiv.style.display = "none";
                        provinceDiv.style.display = "none";
                        cityDiv.style.display = "none";
                        countyDiv.style.display = "none";

                        countryDiv.style.display = "block";
                        document.getElementById("address-label").innerHTML = "国外详细地址:";

                        document.getElementById("in-school").checked = false;
                        document.getElementById("outside-school").checked = false;
                        document.getElementById("not-in-shanghai").checked = false;

                        document.getElementById("baoshan").checked = false;
                        document.getElementById("yanchang").checked = false;
                        document.getElementById("jiading").checked = false;
                        document.getElementById("xinzhalu").checked = false;

                        document.getElementById("do-not-enter").checked = false;
                        document.getElementById("enter-school").checked = false;
                        document.getElementById("leave-school").checked = false;

                        document.getElementById("stay-in-campus").checked = false;
                        document.getElementById("stay-outside").checked = false;

                        document.getElementById("baoshan-radio").checked = false;
                        document.getElementById("yanchang-radio").checked = false;
                        document.getElementById("jiading-radio").checked = false;
                        document.getElementById("xinzhalu-radio").checked = false;

                        provinceSel.value = "";
                        citySel.value = "";
                        countySel.value = "";

                        document.getElementById("address").value = "";
                    }
                    else if(e.target.value=="InChina") {
                        inShanghaiOrNotDiv.style.display = "block";
                        enterSchoolOrNotDiv.style.display = "block";
                        provinceDiv.style.display = "block";

                        countryDiv.style.display = "none";
                        
                        countrySel.value = "";

                        document.getElementById("address-label").innerHTML = "国内详细地址 (省市区县无需重复填写):";
                        document.getElementById("address").value = "";
                    }
                }
                // In Shanghai or not value is changed
                else if(e.target.getAttribute('name')=="inShanghaiOrNot") {
                    if(e.target.value=="InSchool") {
                        campusesToEnterDiv.style.display = "block";
                        stayInCampusOrNotDiv.style.display = "block";
                    }
                    else {
                        campusesToEnterDiv.style.display = "none";
                        stayInCampusOrNotDiv.style.display = "none";
                        campusToStayDiv.style.display = "none";

                        document.getElementById("baoshan").checked = false;
                        document.getElementById("yanchang").checked = false;
                        document.getElementById("jiading").checked = false;
                        document.getElementById("xinzhalu").checked = false;

                        document.getElementById("stay-in-campus").checked = false;
                        document.getElementById("stay-outside").checked = false;

                        document.getElementById("baoshan-radio").checked = false;
                        document.getElementById("yanchang-radio").checked = false;
                        document.getElementById("jiading-radio").checked = false;
                        document.getElementById("xinzhalu-radio").checked = false;
                    }
                }
                // Stay in campus or not value is changed
                else if(e.target.getAttribute('name')=="stayInCampusOrNot") {
                    if(e.target.value=="StayInCampus") {
                        campusToStayDiv.style.display = "block";
                    }
                    else {
                        campusToStayDiv.style.display = "none";
                        document.getElementById("baoshan-radio").checked = false;
                        document.getElementById("yanchang-radio").checked = false;
                        document.getElementById("jiading-radio").checked = false;
                        document.getElementById("xinzhalu-radio").checked = false;
                    }
                }
            })

            // Add province list to the options element
            for (var x in DailyReportScript.provinceObject) {
                provinceSel.options[provinceSel.options.length] = new Option(x, x);
            }
            // Add country list to the options element
            for (var index = 0; index < DailyReportScript.countryObject.length; index++) {
                countrySel.options[countrySel.options.length] = new Option(DailyReportScript.countryObject[index], DailyReportScript.countryObject[index]);
            }
            // Province value is changed
            provinceSel.onchange = function() {
                if(provinceSel.value != "上海")
                    countyDiv.style.display = "none";
                //empty Chapters- and Topics- dropdowns
                countySel.length = 1;
                citySel.length = 1;
                //display correct values
                for (var y in DailyReportScript.provinceObject[this.value]) {
                    citySel.options[citySel.options.length] = new Option(y, y);
                }
                cityDiv.style.display = "block";
            }
            // City value is changed
            citySel.onchange = function() {
                console.log(citySel.value)
                if(citySel.value != "上海市")
                        countyDiv.style.display = "none";
                if (citySel.value != "") {
                    //empty Chapters dropdown
                    countySel.length = 1;
                    //display correct values
                    var z = DailyReportScript.provinceObject[provinceSel.value][this.value];
                    if(citySel.value == "上海市") {
                        countyDiv.style.display = "block";
                    }
                    for (var i = 0; i < z.length; i++) {
                        countySel.options[countySel.options.length] = new Option(z[i], z[i]);
                    }
                }
            }
        },
        methods: {
            submit(dailyreport) {
                dailyreport.studentId = this.currentUser.studentId;
                dailyreport.email = this.currentUser.email;
                dailyreport.physicalCondition = document.querySelector('input[name="physicalCondition"]:checked').value;
                dailyreport.inChinaOrNot = document.querySelector('input[name="inChinaOrNot"]:checked').value;
                dailyreport.province = document.getElementById("province").value;
                dailyreport.city = document.getElementById("city").value;
                dailyreport.county = document.getElementById("county").value;
                dailyreport.country = document.getElementById("country").value;

                this.$store.dispatch("dailyrep/submit", dailyreport).then(
                    () => {
                        this.$router.push( { path: "/daily-report" } );
                    },
                    (error) => {
                        this.message =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();
                    }
                );
                alert("填报成功");
                this.$router.push( { path: "/daily-report" } );
            }
        }
    };
</script>

<style scoped>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+SC:wght@500&display=swap');  
    * {
        font-family: 'Noto Serif SC', serif;
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
</style>