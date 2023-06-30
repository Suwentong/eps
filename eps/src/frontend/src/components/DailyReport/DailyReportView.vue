<template>
    <div class="form-item" id="reportDate-div">
        <label for="reportDate">报送日期: </label>
        <input name="date" type="date" class="left-label" id="reportDate" style="text-align: center; width: 140px; padding-left: 12px;" v-model="date" disabled/>
    </div>
    <div class="form-item" id="physicalCondition-div">
        <label for="phyical-condition">当前身体状况: </label>
        <input type="radio" class="left-label" id="good" name="physicalCondition" value="Good" disabled/>
        <label for="good">良好 (体温不高于37.3)</label>
        <input type="radio" class="left-label" id="discomfort" name="physicalCondition" value="Discomfort" disabled/>
        <label for="discomfort">不适</label>
    </div>
    <div class="form-item" id="symptoms-div">
        <label>症状:</label>
        <input type="checkbox" class="left-label" name="symptoms" id="cold" value="Cold" disabled/>
        <label for="cold">感冒</label>
        <input type="checkbox" class="left-label" name="symptoms" id="cough" value="Cough" disabled/>
        <label for="cough">咳嗽</label>
        <input type="checkbox" class="left-label" name="symptoms" id="fever" value="Fever" disabled/>
        <label for="fever">发热</label>
    </div>
    <div class="form-item" id="temperature-div">
        <label for="temperature">体温:</label>
        <input type="text" class="left-label" name="temperature" id="temperature" disabled/>
        <label for="temperature">℃</label>
    </div>
    <div class="form-item" id="inChinaOrNot-div">
        <label for="in-china-or-not">当天是否在国内:</label>
        <input type="radio" class="left-label" id="in-china" name="inChinaOrNot" value="InChina" disabled/>
        <label for="in-china">国内</label>
        <input type="radio" class="left-label" id="abroad" name="inChinaOrNot" value="Abroad" disabled/>
        <label for="abroad">国外</label>
    </div>
    <div class="form-item" id="inShanghaiOrNot-div">
        <label for="inShanghaiOrNot">当天是否在上海:</label>
        <ErrorMessage name="inShanghaiOrNot" class="error-feedback"/><br>
        <input type="radio" id="in-school" name="inShanghaiOrNot" value="InSchool" disabled/>
        <label for="in-school">在上海 (进学校)</label><br>
        <input type="radio" id="outside-school" name="inShanghaiOrNot" value="OutsideSchool" disabled/>
        <label for="outside-school">在上海 (不进学校)</label><br>
        <input type="radio" id="not-in-shanghai" name="inShanghaiOrNot" value="NotInShanghai" disabled/>
        <label for="not-in-shanghai">不在上海</label>
    </div>
    <div class="form-item" id="campusesToEnter-div">
        <label for="campusesToEnter">当天进校校区:</label>
        <input type="checkbox" class="left-label" name="campusesToEnter" id="baoshan" value="Baoshan" disabled/>
        <label for="baoshan">宝山</label>
        <input type="checkbox" class="left-label" name="campusesToEnter" id="yanchang" value="Yanchang" disabled/>
        <label for="yanchang">延长</label>
        <input type="checkbox" class="left-label" name="campusesToEnter" id="jiading" value="Jiading" disabled/>
        <label for="jiading">嘉定</label>
        <input type="checkbox" class="left-label" name="campusesToEnter" id="xinzhalu" value="Xinzhalu" disabled/>
        <label for="xinzhalu">新闸路</label>
    </div>
    <div class="form-item" id="stayInCampusOrNot-div">
        <label>当晚住校且当天不离校:</label>
        <input type="radio" class="left-label" name="stayInCampusOrNot" id="stay-in-campus" value="StayInCampus" disabled/>
        <label for="stay-in-campus">是</label>
        <input type="radio" class="left-label" name="stayInCampusOrNot" id="stay-outside" value="StayOutside" disabled/>
        <label for="stay-outside">否</label>
    </div>
    <div class="form-item" id="campusToStay-div">
        <label for="campusToStay">所在校区:</label>
        <input type="radio" class="left-label" name="campusToStay" id="baoshan-radio" value="Baoshan" disabled/>
        <label for="baoshan-radio">宝山</label>
        <input type="radio" class="left-label" name="campusToStay" id="yanchang-radio" value="Yanchang" disabled/>
        <label for="yanchang-radio">延长</label>
        <input type="radio" class="left-label" name="campusToStay" id="jiading-radio" value="Jiading" disabled/>
        <label for="jiading-radio">嘉定</label>
        <input type="radio" class="left-label" name="campusToStay" id="xinzhalu-radio" value="Xinzhalu" disabled/>
        <label for="xinzhalu-radio">新闸路</label>
    </div>
    <div class="form-item" id="enterSchoolOrNot-div">
        <label for="enterSchoolOrNot">日报提交后是否需要申请明天的进出校: </label><br>
        <input type="radio" id="do-not-enter" name="enterSchoolOrNot" value="DoNotEnter" disabled/>
        <label for="do-not-enter">不需要进出校, 不申请</label><br>
        <input type="radio" id="enter-school" name="enterSchoolOrNot" value="EnterSchool" disabled/>
        <label for="enter-school">需要申请进校</label><br>
        <input type="radio" id="leave-school" name="enterSchoolOrNot" value="LeaveSchool" disabled/>
        <label for="leave-school">需要申请出校</label>
    </div>
    <div class="form-item" id="province-div">
        <label for="province">当天所在省: </label>
        <select name="province" class="left-label" id="province" style="width: 100px; margin-left: 35px;" disabled>
            <option value="" selected="selected">选择省份</option>
        </select>
    </div>
    <div class="form-item" id="city-div">
        <label for="city">当天所在市: </label>
        <select name="city" class="left-label" id="city" style="width: 100px; margin-left: 35px;" disabled>
            <option value="" selected="selected">选择市</option>
        </select>
    </div>
    <div class="form-item" id="county-div">
        <label for="county">当天所在县区: </label>
        <select name="county" class="left-label" id="county" style="width: 100px; margin-left: 20px;" disabled>
            <option value="" selected="selected">选择县区</option>
        </select>
    </div>
    <div class="form-item" id="country-div">
        <label for="country">当天所在国家: </label>
        <select name="country" class="left-label" id="country" style="width: 100px; margin-left: 20px;" disabled>
            <option value="" selected="selected">选择国家</option>
        </select>
    </div>
    <div class="form-item" id="address-div">
        <label for="address" id="address-label">详细地址:</label><br>
        <input type="text" name="address" id="address" disabled/>
    </div>
    <div class="form-item" id="isHomeAddress-div">
        <label for="isHomeAddress">是否家庭地址: </label>
        <input type="radio" class="left-label" id="home-address" name="isHomeAddress" value="HomeAddress" disabled/>
        <label for="home-address">家庭地址</label>
        <input type="radio" class="left-label" id="not-home-address" name="isHomeAddress" value="NotHomeAddress" disabled/>
        <label for="not-home-address">不是家庭地址</label>
        <ErrorMessage name="isHomeAddress" class="error-feedback"/>
    </div>

    <div class="button-container">
        <button class="submit-button" @click="returnBack">返回</button>
    </div>
</template>

<script>
    import DailyReportService from "../../services/daily-report.service";
    
    export default {
        name: "DailyReportView",
        data() {
            return {
                date: localStorage.date
            }
        },
        mounted() {
            DailyReportService.getByDate(this.currentUser.studentId, this.date).then(
                (response) => {
                    console.log(response.data)

                    // Physical condition == Good
                    if (response.data.physicalCondition == "Good") {
                        document.getElementById("good").checked = true;
                        document.getElementById("symptoms-div").style.display = "none";
                        document.getElementById("temperature-div").style.display = "none";
                    }
                    // Physical Condition == Discomfort
                    else if (response.data.physicalCondition == "Discomfort") {
                        document.getElementById("discomfort").checked = true;
                        response.data.symptoms.forEach( symptom => {
                            console.log(symptom)
                            if (symptom == "Cold")
                                document.getElementById("cold").checked = true;
                            else if (symptom == "Cough")
                                document.getElementById("cough").checked = true;
                            else if (symptom == "Fever")
                                document.getElementById("fever").checked = true;
                        })
                        document.getElementById("temperature").value = response.data.temperature;
                    }

                    // In China or Not == In China
                    if (response.data.inChinaOrNot == "InChina") {
                        document.getElementById("in-china").checked = true;
                        document.getElementById("country-div").style.display = "none";
                        // In Shanghai or not == In School
                        if (response.data.inShanghaiOrNot == "InSchool") {
                            document.getElementById("in-school").checked = true;
                            
                            // Campuses to enter
                            response.data.campusesToEnter.forEach(campus => {
                                if (campus == "Baoshan")
                                    document.getElementById("baoshan").checked = true;
                                else if (campus == "Yanchang")
                                    document.getElementById("yanchang").checked = true;
                                else if (campus == "Jiading")
                                    document.getElementById("jiading").checked = true;
                                else if (campus == "Xinzhalu")
                                    document.getElementById("xinzhalu").checked = true;
                            })

                            // Stay in Campus or Not
                            if (response.data.stayInCampusOrNot == "StayInCampus") {
                                document.getElementById("stay-in-campus").checked = true;
                                if (response.data.campusToStay == "Baoshan")
                                    document.getElementById("baoshan-radio").checked = true;
                                else if (response.data.campusToStay == "Yanchang")
                                    document.getElementById("yanchang-radio").checked = true;
                                else if (response.data.campusToStay == "Jiading")
                                    document.getElementById("jiading-radio").checked = true;
                                else if (response.data.campusToStay == "Xinzhalu")
                                    document.getElementById("xinzhalu-radio").checked = true;
                            }
                            else if (response.data.stayInCampusOrNot == "StayOutside") {
                                document.getElementById("stay-outside").checked = true;
                                document.getElementById("campusToStay-div").style.display = "none";
                            }

                            var provinceSel = document.getElementById("province");
                            var citySel = document.getElementById("city");
                            
                            provinceSel.options[provinceSel.options.length] = new Option(response.data.province, response.data.province);
                            provinceSel.value = response.data.province;

                            citySel.options[citySel.options.length] = new Option(response.data.city, response.data.city);
                            citySel.value = response.data.city;

                            if (response.data.county == "Shanghai") {
                                var countySel = document.getElementById("county");
                                countySel.options[countySel.options.length] = new Option(response.data.county, response.data.county);
                                countySel.value = response.data.county;
                            }
                            else
                            document.getElementById("county-div").style.display = "none";
                        }
                        // In Shanghai or not == Outside School
                        else if (response.data.inShanghaiOrNot == "OutsideSchool") {
                            document.getElementById("outside-school").checked = true;
                            document.getElementById("campusesToEnter-div").style.display = "none";
                            document.getElementById("stayInCampusOrNot-div").style.display = "none";
                            document.getElementById("campusToStay-div").style.display = "none";
                        }
                        // In Shanghai or not == Not In Shanghai
                        else if (response.data.inShanghaiOrNot == "NotInShanghai") {
                            document.getElementById("not-in-shanghai").checked = true;
                            document.getElementById("campusesToEnter-div").style.display = "none";
                            document.getElementById("stayInCampusOrNot-div").style.display = "none";
                            document.getElementById("campusToStay-div").style.display = "none";
                        }
                        
                    }
                    // In China or Not == Abroad
                    else if (response.data.inChinaOrNot == "Abroad") {
                        document.getElementById("abroad").checked = true;

                        document.getElementById("inShanghaiOrNot-div").style.display = "none";
                        document.getElementById("campusesToEnter-div").style.display = "none";
                        document.getElementById("stayInCampusOrNot-div").style.display = "none";
                        document.getElementById("campusToStay-div").style.display = "none";
                        document.getElementById("enterSchoolOrNot-div").style.display = "none";

                        document.getElementById("province-div").style.display = "none";
                        document.getElementById("city-div").style.display = "none";
                        document.getElementById("county-div").style.display = "none";
                        
                        // Add country list to the options element
                        var countrySel = document.getElementById("country");
                        countrySel.options[countrySel.options.length] = new Option(response.data.country, response.data.country);
                        countrySel.value = response.data.country;
                    }

                    // Enter School or Not
                    if (response.data.enterSchoolOrNot == "DoNotEnter")
                        document.getElementById("do-not-enter").checked = true;
                    else if (response.data.enterSchoolOrNot == "EnterSchool")
                        document.getElementById("enter-school").checked = true;
                    else if (response.data.enterSchoolOrNot == "LeaveSchool")
                        document.getElementById("leave-school").checked = true;
                    
                    // Address
                    document.getElementById("address").value = response.data.address;

                    // Is Home Address
                    if (response.data.isHomeAddress == "HomeAddress")
                        document.getElementById("home-address").checked = true;
                    else if (response.data.isHomeAddress == "NotHomeAddress")
                        document.getElementById("not-home-address").checked = true;
                }
            )
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        methods: {
            returnBack() {
                scroll(0,0);
                this.$router.push( { path: "/daily-report" } );
            }
        }
    }
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
    /* input[type="radio"]:disabled {
        -webkit-appearance: none;
        display: inline-block;
        width: 12px;
        height: 12px;
        padding: 0px;
        background-clip: content-box;
        border: 2px solid #bbbbbb;
        background-color: white;
        border-radius: 50%;
    }
    input[type="radio"]:checked {
        border: 2px solid black;
        background-color: ;
    }   */
</style>