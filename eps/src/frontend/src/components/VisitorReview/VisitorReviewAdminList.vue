<template>
    <div style="display: inline-block; width: 100%;">
        <h3 class="header">访客审核</h3>
        <p>申请总数: {{ visitorReviewArray.length }}</p>
        <p>已审核: {{ reviewed }}, 未审核: {{ notReviewed }}</p>

        <div class="search-container">
            <input class="search" type="text" v-model="searchTerm" placeholder="搜索">
        </div>
        <table class="content-table table table-striped" id="example">
            <thead>
                <th>ID</th>
                <th>提交日期</th>
                <th>访客姓名</th>
                <th>访客证件号码</th>
                <th>访客电话</th>
                <th>状态</th>
                <th>操作</th>
            </thead>
            <tbody v-if="visitorReviewArray.length == 0">
                <td colspan="4" style="text-align: center;">暂无数据</td>
            </tbody>
            <tbody v-else v-for="visitorReview in paginatedData" :key="visitorReview.id" style="text-align: center;">
                <td>{{ visitorReview.id }}</td>
                <td>{{ visitorReview.submitDate }}</td>
                <td>{{ visitorReview.visitorName }}</td>
                <td>{{ visitorReview.idNumber }}</td>
                <td>{{ visitorReview.phoneNumber }}</td>
                <td v-if="visitorReview.status == 0" style="color: #f5bd05 !important;">等待审核</td>
                <td v-else style="color: #08a10b !important;">已审核</td>
                <td v-if="visitorReview.status == 0"><button class="table-button" @click="review(visitorReview.submitDate, visitorReview.studentId)">审核</button></td>
                <td v-else><button class="table-button" @click="review(visitorReview.submitDate, visitorReview.studentId)">查看</button></td>
            </tbody>
        </table>
        <div style="text-align: right;">
            <button class="pagination-button" :disabled="currentPage === 1" @click="prevPage">ᐸ</button>
            <span v-if="totalPages == 0" style="margin: 0px; ">0 of 0</span>
            <span v-else style="margin: 0px; ">{{ currentPage }} of {{ totalPages }}</span>
            <button class="pagination-button" :disabled="currentPage === totalPages" @click="nextPage">ᐳ</button>
        </div>
    </div>
</template>

<script>
    import VisitorReviewService from "../../services/visitor-review.service";

    export default {
        name: "VisitorReviewAdminList",
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
                visitorReviewArray: [],
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
            VisitorReviewService.getAll().then(
                (response) => {
                    this.visitorReviewArray = response.data;

                    // Remove T from submitDate (2023-04-13T08:00:00 -> 2023-04-13 08:00:00)
                    for(let index = 0; index < this.visitorReviewArray.length; index++) {
                        this.visitorReviewArray[index].submitDate = this.visitorReviewArray[index].submitDate.substring(0, 19).replace("T", " ");
                        if (this.visitorReviewArray[index].status == 0){ 
                            this.notReviewed++;
                            this.visitorReviewArray[index].frontendStatus = "等待审核";
                        }
                        else {
                            this.reviewed++;
                            this.visitorReviewArray[index].frontendStatus = "已审核";
                        }

                        // Add properties to let search work correctly (fix later)
                        // if (this.visitorReviewArray[index].applicationType == "leave")
                        //     this.visitorReviewArray[index].frontendApplicationType = "出校申请";
                        // else
                        //     this.visitorReviewArray[index].frontendApplicationType = "进校申请";
                    }
                    this.visitorReviewArray.sort(function(a, b) { return(b.id - a.id)});
                    this.totalPages = Math.ceil(this.visitorReviewArray.length / this.perPage);
                }
            )
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            paginatedData() {
                const searchTerm = this.searchTerm.toLowerCase();
                const filteredData = this.visitorReviewArray.filter(row =>
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
            review(submitDate, studentId) {
                localStorage.submitDate = submitDate;
                localStorage.studentId = studentId;
                this.$router.push( { path: "/visitor-review-admin/review" } );
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
                const filteredData = this.visitorReviewArray.filter(row =>
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
