import { createWebHistory, createRouter } from "vue-router";
import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
// lazy-loaded
const DailyReportList = () => import("./components/DailyReport/DailyReportList.vue")
const DailyReportAdminList = () => import("./components/DailyReport/DailyReportAdminList.vue")
const DailyReportStudentList = () => import("./components/DailyReport/DailyReportStudentList.vue")
const DailyReportView = () => import("./components/DailyReport/DailyReportView.vue")
const DailyReportStudentView = () => import("./components/DailyReport/DailyReportStudentView.vue")
const DailyReport = () => import("./components/DailyReport/DailyReport.vue")
const Profile = () => import("./components/Profile.vue")
const LeaveSchoolApplicationList = () => import("./components/LeaveSchool/LeaveSchoolApplicationList.vue")
const LeaveSchoolAdminApplicationList = () => import("./components/LeaveSchool/LeaveSchoolAdminApplicationList.vue")
const LeaveSchool = () => import("./components/LeaveSchool/LeaveSchool.vue")
const EnterSchool = () => import("./components/LeaveSchool/EnterSchool.vue")
const LeaveSchoolReview = () => import("./components/LeaveSchool/LeaveSchoolReview.vue")
const LeaveShanghai = () => import("./components/LeaveShanghai/LeaveShanghai.vue")
const LeaveShanghaiApplicationList = () => import("./components/LeaveShanghai/LeaveShanghaiApplicationList.vue")
const LeaveShanghaiAdminApplicationList = () => import("./components/LeaveShanghai/LeaveShanghaiAdminApplicationList.vue")
const ReturnToShanghai = () => import("./components/LeaveShanghai/ReturnToShanghai.vue")
const LeaveShanghaiReview = () => import("./components/LeaveShanghai/LeaveShanghaiReview.vue")
const VisitorReviewList = () => import("./components/VisitorReview/VisitorReviewList.vue")
const VisitorReviewAdminList = () => import("./components/VisitorReview/VisitorReviewAdminList.vue")
const VisitorReview = () => import("./components/VisitorReview/VisitorReview.vue")
const VisitorAdminReview = () => import("./components/VisitorReview/VisitorAdminReview.vue")
//const BoardAdmin = () => import("./components/BoardAdmin.vue")
//const BoardModerator = () => import("./components/BoardModerator.vue")
const BoardUser = () => import("./components/BoardUser.vue")

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/home",
    component: Home,
  },
  {
    path: "/login",
    component: Login,
    meta: {
      hideNavbar: true,
    }
  },
  {
    path: "/register",
    component: Register,
    meta: {
      hideNavbar: true,
    }
  },
  {
    path: "/profile",
    name: "profile",
    // lazy-loaded
    component: Profile,
  },
  {
    path: "/daily-report",
    name: "daily-report-list",
    component: DailyReportList,
  },
  {
    path: "/daily-report-admin",
    name: "daily-report-admin-list",
    component: DailyReportAdminList
  },
  {
    path: "/daily-report-admin/student-list",
    name: "daily-report-student-list",
    component: DailyReportStudentList
  },
  {
    path: "/daily-report/submit",
    name: "daily-report",
    component: DailyReport,
  },
  {
    path: "/daily-report/view",
    name: "daily-report-view",
    component: DailyReportView
  },
  {
    path: "/daily-report-admin/student-list/view",
    name: "daily-report-student-view",
    component: DailyReportStudentView
  },
  {
    path: "/leave-school-application",
    name: "leave-school-application-list",
    component: LeaveSchoolApplicationList
  },
  {
    path: "/leave-school-admin-application-list",
    name: "leave-school-admin-application-list",
    component: LeaveSchoolAdminApplicationList
  },
  {
    path: "/leave-school-application/leave",
    name: "leave-school",
    component: LeaveSchool
  },
  {
    path: "/leave-school-application/enter",
    name: "enter-school",
    component: EnterSchool
  },
  {
    path: "/leave-school-admin-application-list/review",
    name: "leave-school-review",
    component: LeaveSchoolReview
  },
  {
    path: "/leave-shanghai-application",
    name: "leave-shanghai-application-list",
    component: LeaveShanghaiApplicationList
  },
  {
    path: "/leave-shanghai-admin-application-list",
    name: "leave-shanghai-admin-application-list",
    component: LeaveShanghaiAdminApplicationList
  },
  {
    path: "/leave-shanghai-application/leave",
    name: "leave-shanghai",
    component: LeaveShanghai
  },
  {
    path: "/leave-shanghai-application/return",
    name: "return-to-shanghai",
    component: ReturnToShanghai
  },
  {
    path: "/leave-shanghai-admin-application-list/review",
    name: "leave-shanghai-review",
    component: LeaveShanghaiReview
  },
  {
    path: "/visitor-review",
    name: "visitor-review-list",
    component: VisitorReviewList
  },
  {
    path: "/visitor-review-admin",
    name: "visitor-review-admin-list",
    component: VisitorReviewAdminList
  },
  {
    path: "/visitor-review/submit",
    name: "visitor-review",
    component: VisitorReview
  },
  {
    path: "/visitor-review-admin/review",
    name: "visitor-admin-review",
    component: VisitorAdminReview
  },
//  {
//    path: "/admin",
//    name: "admin",
//    // lazy-loaded
//    component: BoardAdmin,
//  },
//  {
//    path: "/mod",
//    name: "moderator",
//    // lazy-loaded
//    component: BoardModerator,
//  },
  {
    path: "/user",
    name: "user",
    // lazy-loaded
    component: BoardUser,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login',
                         '/register',
                         '/home',
                         '/profile',
                         '/daily-report', 
                         '/daily-report/submit',
                         '/daily-report/view',
                         '/leave-school-application',
                         '/leave-school-application/leave',
                         '/leave-school-application/enter',
                         '/visitor-review',
                         '/visitor-review/submit',
                         '/leave-shanghai-application',
                         '/leave-shanghai-application/leave',
                         '/leave-shanghai-application/return',
                         '/leave-school-admin-application-list',
                         '/leave-school-admin-application-list/review',
                         '/leave-shanghai-admin-application-list',
                         '/leave-shanghai-admin-application-list/review',
                         '/visitor-review-admin',
                         '/visitor-review-admin/review',
                         '/daily-report-admin',
                         '/daily-report-admin/student-list',
                         '/daily-report-admin/student-list/view'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');
  
    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
      next('/login');
    } else {
      next();
    }
});

export default router;
