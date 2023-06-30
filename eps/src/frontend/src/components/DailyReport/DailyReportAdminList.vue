<template>
    <div style="display: inline-block; width: 100%;">
        <h3 class="header">每日报送管理</h3>

        <div class="search-container">
            <input class="search" type="text" v-model="searchTerm" placeholder="搜索">
        </div>
        <table class="content-table table table-striped" id="example">
            <thead>
                <th>ID</th>
                <th>学工号</th>
                <th>姓名</th>
                <th>邮箱</th>
                <th>学院</th>
                <th></th>
            </thead>
            <tbody v-if="userArray.length == 0">
                <td colspan="6" style="text-align: center;">暂无数据</td>
            </tbody>
            <tbody v-else v-for="user in paginatedData" :key="user.id" style="text-align: center;">
                <td>{{ user.id }}</td>
                <td>{{ user.studentId }}</td>
                <td>{{ user.username }}</td>
                <td>{{ user.email }}</td>
                <td>{{ user.department }}</td>
                <td><button class="table-button" @click="review(user.studentId, user.username)">查看</button></td>
            </tbody>
        </table>
    </div>
</template>

<script>
    import UserService from "../../services/user.service";

    export default {
        name: "DailyReportAdminList",
        props: {
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
                userArray: [],
                reviewed: 0,
                notReviewed: 0,
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
            UserService.getAllUsers().then(
                (response) => {
                    this.userArray = response.data;

                    // // Remove T from submitDate (2023-04-13T08:00:00 -> 2023-04-13 08:00:00)
                    // for (let index = 0; index < this.userArray.length; index++) {

                    //     // Add properties to let search work correctly (fix later)
                    //     if (this.userArray[index].applicationType == "leave")
                    //         this.userArray[index].frontendApplicationType = "出校申请";
                    //     else
                    //         this.userArray[index].frontendApplicationType = "进校申请";
                    // }
                    
                    this.userArray.sort(function(a, b) { return(b.id - a.id)});
                    this.totalPages = Math.ceil(this.userArray.length / this.perPage);
                }
            )
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            paginatedData() {
                const searchTerm = this.searchTerm.toLowerCase();
                const filteredData = this.userArray.filter(row =>
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
            review(studentId, username) {
                localStorage.studentId = studentId;
                localStorage.username = username;
                this.$router.push( { path: "/daily-report-admin/student-list" } );
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
                const filteredData = this.userArray.filter(row =>
                    Object.values(row).some(value =>
                        String(value).toLowerCase().includes(searchTerm)
                    )
                );
                this.totalPages = Math.ceil(filteredData.length / this.perPage)
            }
        }
    }
</script>

<style scoped>
* {
        margin-left: 60px;
    }
    .header{
        margin: 20px 0px 40px 20px;
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
</style>
