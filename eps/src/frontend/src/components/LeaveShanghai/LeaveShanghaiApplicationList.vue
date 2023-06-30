<template>
    <div>
        <h3 class="header">返沪离沪申请与查询</h3>

        <div class="search-container">
            <input class="search" type="text" v-model="searchTerm" placeholder="搜索">
        </div>
        <table class="content-table table table-striped" id="example">
            <thead style="text-align: center;">
                <th>提交日期</th>
                <th>操作</th>
                <th>状态</th>
            </thead>
            <tbody v-if="leaveShanghaiArray.length == 0">
                <td colspan="4" style="text-align: center;">暂无数据</td>
            </tbody>
            <tbody v-else v-for="leaveApplication in paginatedData" :key="leaveApplication.submitDate" style="text-align: center;">
                <td>{{ leaveApplication.submitDate }}</td>
                <td v-if="leaveApplication.applicationType == 'leave'">离沪申请</td>
                <td v-if="leaveApplication.applicationType == 'return'">返沪申请</td>
                <td v-if="leaveApplication.status == 0" style="color: #f5bd05 !important;">审核中</td>
                <td v-if="leaveApplication.status == 1" style="color: #08a10b !important;">同意</td>
                <td v-if="leaveApplication.status == 2" style="color: #b00707 !important;">拒绝</td>
            </tbody>
        </table>
        <div>
            <div style="display: inline-block;">
                <button @click="leaveApplication">申请离沪</button>
                <button @click="returnApplication">申请返沪</button>
            </div>

            <div style="display: inline-block; float: right;">
                <button class="pagination-button" :disabled="currentPage === 1" @click="prevPage">ᐸ</button>
                <span v-if="totalPages == 0" style="margin: 0px; ">0 of 0</span>
                <span v-else style="margin: 0px; ">{{ currentPage }} of {{ totalPages }}</span>
                <button class="pagination-button" :disabled="currentPage === totalPages" @click="nextPage">ᐳ</button>
            </div>
        </div>
    </div>
</template>

<script>
    import LeaveShanghaiService from "../../services/leave-shanghai.service";

    export default {
        name: "LeaveShanghaiApplicationList",
        props:{
            data: {
                type: Array,
                required: true,
            },
            perPage: {
                type: Number,
                default: 5,
            },
        },
        data() {
            return {
                leaveShanghaiArray: [],
                currentPage: 1,
                totalPages: 0,
                searchTerm: ''
            };
        },
        watch: {
            searchTerm: function() {
                this.updateTotalPages();
            },
        },
        mounted() {
            LeaveShanghaiService.getAllByStudentId(this.currentUser.studentId).then(
                (response) => {
                    this.leaveShanghaiArray = response.data;
                    for (let index = 0; index < this.leaveShanghaiArray.length; index++) {
                        this.leaveShanghaiArray[index].submitDate = this.leaveShanghaiArray[index].submitDate.substring(0, 19).replace("T", " ");
                        // Add properties to let search work correctly (fix later)
                        if (this.leaveShanghaiArray[index].status == 0)
                            this.leaveShanghaiArray[index].frontendStatus = "审核中";
                        else if (this.leaveShanghaiArray[index].status == 1)
                            this.leaveShanghaiArray[index].frontendStatus = "同意";
                        else if (this.leaveShanghaiArray[index].status == 2)
                            this.leaveShanghaiArray[index].frontendStatus = "拒绝";

                        if (this.leaveShanghaiArray[index].applicationType == "leave")
                            this.leaveShanghaiArray[index].frontendApplicationType = "出校申请";
                        else
                            this.leaveShanghaiArray[index].frontendApplicationType = "进校申请";
                    }
                    this.leaveShanghaiArray.sort(function(a, b) { return(b.id - a.id)});
                    this.totalPages = Math.ceil(this.leaveShanghaiArray.length / this.perPage);
                }
            )
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            paginatedData() {
                const searchTerm = this.searchTerm.toLowerCase();
                const filteredData = this.leaveShanghaiArray.filter(row =>
                    Object.values(row).some(value =>
                        String(value).toLowerCase().includes(searchTerm)
                    )
                );
                const startIndex = (this.currentPage - 1) * this.perPage;
                const endIndex = startIndex + this.perPage;
                return filteredData.slice(startIndex, endIndex);
            }
        },
        methods: {
            leaveApplication() {
                this.$router.push( { path: "/leave-shanghai-application/leave" } );
            },
            returnApplication() {
                this.$router.push( { path: "/leave-shanghai-application/return" } );
            },
            nextPage() {
                if (this.currentPage < this.totalPages) {
                    this.currentPage++;
                }
            },
            prevPage() {
                if (this.currentPage > 1) {
                    this.currentPage--;
                }
            },
            updateTotalPages() {
                const searchTerm = this.searchTerm.toLowerCase();
                const filteredData = this.leaveShanghaiArray.filter(row =>
                    Object.values(row).some(value =>
                        String(value).toLowerCase().includes(searchTerm)
                    )
                );
                console.log(filteredData)
                this.totalPages = Math.ceil(filteredData.length / this.perPage)
            }
        }
    }
</script>

<style scoped>
    .header{
        margin: 20px 0px 20px 0px;
        padding-left: 10px;
        border-left: #1A284F solid 3.5px;
        color: #1A284F;
    }
    .content-table {
        width: 100%;
        margin-top: 30px;
        border-collapse: collapse;
        border-radius: 5px 5px 0 0;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    }
    .content-table thead th {
        text-align: center;
        background-color: #1A284F;
        color: #fff;
        font-size: bold;
    }
    .content-table thead th, 
    .content-table tbody td {
        padding: 12px 15px;
    }
    .content-table tbody {
        border-bottom: 1px solid #dddddd;
    }
    .content-table tbody:nth-of-type(even) {
        background-color: #f3f3f3;
    }
    .content-table tbody:last-of-type {
        border-bottom: 2px solid #1A284F;
    }
    .content-table tbody:hover {
        color: #1A284F;
        font-weight: bold;
    }
    .table-button {
        color: white;
        margin: 0px;
        background-color: #1A284F;
        width: 60px;
        height: 30px;
        border-radius: 5px !important;
        border: none;
        outline: none;
        cursor: pointer;
        font-size: 1em;
    }
    .pagination-button {
        color: #1A284F;
        background-color: white;
        margin: 3px;
        width: 30px;
        height: 30px;
        border-radius: 5px !important;
        border: none;
        outline: none;
        cursor: pointer;
        font-size: 1em;
    }
    .search-container {
        text-align: right;
        margin: 0 auto;
    }
    .search {
        border: #777 solid 2px;
        border-radius: 20px;
        outline: none;
        font-size: 1em;
        padding: 5px 20px;
        width: 20%;
    }
    button {
        color: white;
        margin: 5px;
        margin-top: 20px;
        background-color: #1A284F;
        width: 80px;
        height: 40px;
        border-radius: 5px !important;
        border: none;
        outline: none;
        cursor: pointer;
        font-size: 1em;
    }
</style>
