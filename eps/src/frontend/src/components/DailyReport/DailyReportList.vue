<template>
    <ul class="daily-report-list">
        <li v-for="[key, value] in dateMap" :key="key" class="daily-report">
            <a class="daily-report-record">
                {{ key }} <a v-if="value == '1'" @click="view(key)">，已填报 ✔</a><a v-else-if="value == '0'" @click="submit(key)">，未填报，请点击此处补报 ✖</a>
                <font-awesome-icon class="fa" icon="arrow-up-right-from-square" /> 
            </a>
        </li>
    </ul>
</template>

<script>
    import DailyReportService from "../../services/daily-report.service";
    export default {
        name: "DailyReportList",
        data() {
            return {
                dateMap: [],
                dailyReportList: []
            };
        },
        mounted() {
            this.dateMap = new Map();

            for (let index = 0; index < 30; index++) {
                const start = new Date(); 
                const date = new Date(start.getFullYear(),start.getMonth(),start.getDate()-index);
                this.dateMap.set(this.toJSONLocal(date), 0);
            }

            DailyReportService.getAllByStudentId(this.currentUser.studentId).then(
                (response) => {
                    this.dailyReportList = response.data;
                    for (let index = 0; index < this.dailyReportList.length; index++) {
                        if (this.dateMap.has(this.dailyReportList[index].date))
                            this.dateMap.set(this.dailyReportList[index].date, 1);
                    }
                }
            )
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            getAll() {
                return this.getDailyReports()
            }
        },
        methods: {
            getDailyReports(items) {
                return(this.$store.dispatch("dailyrep/getAll"), items);
            },
            toJSONLocal(date) {
                var local = new Date(date);
                local.setMinutes(date.getMinutes() - date.getTimezoneOffset());
                return local.toJSON().slice(0, 10);
            },
            submit(key) {
                localStorage.date = key;
                this.$router.push( { path: "/daily-report/submit" } );
            },
            view(key) {
                localStorage.date = key;
                this.$router.push( { path: "/daily-report/view" } );
            }
        }
    }
</script>

<style scoped>
    *{
        color: #1A284F;
    }
    .daily-report-list{
        list-style-type: "\00BB";
        display:inline-block;
        margin-top: 30px;
        padding-left: 0;
        width: 100%;
    }
    .daily-report{
        padding: 8px;
    }
    .daily-report:hover{
        background-color: #eee;
    }
    a:link {
      text-decoration: none;
    }
    a:visited {
        text-decoration: none;
    }
    a:hover {
        text-decoration: none;
    }
    a:active {
        text-decoration: none;
    }
    .fa{
        width: 15px;
        height: 15px;
        float: right;
    }
</style>
