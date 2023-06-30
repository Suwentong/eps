<template>
  <div id="app">
    <Navbar v-if="!$route.meta.hideNavbar" />

    <!-- User Navbar -->
    <nav class="navbar navbar-expand navbar-light" id="nav" v-if="!$route.meta.hideNavbar && !showAdminBoard">
      <!-- <a href="/" class="navbar-brand">bezKoder</a> -->
      <a href="/home"><img src="@/img/shu_logo.png" class="logo"></a>
      <h1 class="logo-name">上海大学疫情防控系统</h1>
      <div class="navbar-nav mr-auto">
        <!-- <li class="nav-item">
          <router-link to="/home" class="nav-link">
            <font-awesome-icon icon="home" /> Home
          </router-link>
        </li> -->
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/admin" class="nav-link">Admin Board</router-link>
        </li>
        <li v-if="showModeratorBoard" class="nav-item">
          <router-link to="/mod" class="nav-link">Moderator Board</router-link>
        </li>
        <!-- <li class="nav-item">
          <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link>
        </li> -->
      </div>

      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link" id="nav-link">
            <font-awesome-icon icon="user-plus" /> 注册
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link" id="nav-link">
            <font-awesome-icon icon="sign-in-alt" /> 登录
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link" id="nav-link" >
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" @click.prevent="logOut" id="nav-link">
            <font-awesome-icon icon="sign-out-alt" /> 退出
          </a>
        </li>
      </div>
    </nav>
    
    <!-- User Side Navbar-->
    <nav id="side-nav" v-if="!$route.meta.hideNavbar && !showAdminBoard">
      <div>
        <ul>
          <li class="nav-item">
            <router-link to="/home" class="nav-link" id="side-nav-link">
              <font-awesome-icon class="fa" icon="house" />
              <span class="nav-name">首页</span>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/profile" class="nav-link" id="side-nav-link">
              <font-awesome-icon class="fa" icon="user" />
              <span class="nav-name">个人信息</span>
            </router-link> 
          </li>
          <li class="nav-item">
            <router-link to="/daily-report" class="nav-link" id="side-nav-link" >
              <font-awesome-icon class="fa" icon="file-lines" />
              <span class="nav-name">每日一报</span>
            </router-link> 
          </li>
          <li class="nav-item">
            <router-link to="/leave-school-application" class="nav-link" id="side-nav-link">
              <font-awesome-icon class="fa" icon="person-walking-arrow-right" />
              <span class="nav-name">师生进出校门申请与查询</span>
            </router-link> 
          </li>
          <li class="nav-item">
            <router-link to="/leave-shanghai-application" class="nav-link" id="side-nav-link">
              <font-awesome-icon class="fa" icon="arrow-right-to-city" />
              <span class="nav-name">返沪离沪申请与审核</span>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/visitor-review" class="nav-link" id="side-nav-link">
              <font-awesome-icon class="fa" icon="users" />
              <span class="nav-name">访客申请</span>
            </router-link>
          </li>
        </ul>
      </div>
    </nav>

    <div>
      <!-- Admin Navbar -->
      <nav class="navbar navbar-expand navbar-light" id="admin-nav" v-if="showAdminBoard">
        <h1 class="logo-name" style="margin: 14px 0 14px 280px;">上海大学疫情防控后台管理系统</h1>

        <div v-if="currentUser" class="navbar-nav ml-auto">
          <li class="nav-item">
            <div class="nav-link" id="nav-link">
              <font-awesome-icon icon="user" />
              {{ currentUser.username }}
            </div>
            <!-- <router-link to="/profile" class="nav-link" id="nav-link" >
            </router-link> -->
          </li>
          <li class="nav-item">
            <a class="nav-link" @click.prevent="logOut" id="nav-link">
              <font-awesome-icon icon="sign-out-alt" /> 退出
            </a>
          </li>
        </div>
      </nav>

      <!-- Admin Side Navbar -->
      <nav id="admin-side-nav" v-if="showAdminBoard">
        <div>
          <ul style="list-style-type: none;">
            <li><img src="@/img/shu_logo_no_text.png" class="no-text-logo"></li>
            <li class="admin-nav-item">
              <router-link to="/daily-report-admin" class="nav-link" id="admin-side-nav-link">
                <font-awesome-icon class="fa" icon="file-lines" />
                <span class="admin-nav-name">每日报送管理</span>
              </router-link>
            </li>
            <li class="admin-nav-item">
              <router-link to="/leave-school-admin-application-list" class="nav-link" id="admin-side-nav-link">
                <font-awesome-icon class="fa" icon="person-walking-arrow-right" />
                <span class="admin-nav-name">师生进出校门申请审核</span>
              </router-link>
            </li>
            <li class="admin-nav-item">
              <router-link to="/leave-shanghai-admin-application-list" class="nav-link" id="admin-side-nav-link">
                <font-awesome-icon class="fa" icon="arrow-right-to-city" />
                <span class="admin-nav-name">返沪离沪申请审核</span>
              </router-link>
            </li>
            <li class="admin-nav-item">
              <router-link to="/visitor-review-admin" class="nav-link" id="admin-side-nav-link">
                <font-awesome-icon class="fa" icon="users" />
                <span class="admin-nav-name">访客审核</span>
              </router-link>
            </li>
          </ul>
        </div>
      </nav>
    </div>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import img from '@/assets/logo.png'

export default {
  data() {
    return {
      image: img
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser) {
        return this.currentUser.role == "ADMIN";
      }
      
      // if (this.currentUser && this.currentUser['roles']) {
      //   return this.currentUser['roles'].includes('ROLE_ADMIN');
      // }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_MODERATOR');
      }

      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+SC:wght@500&display=swap');  
  * {
    font-family: 'Noto Serif SC', serif;
  }
  .logo {
    width: 240px;
    height: 65px;
  }
  .no-text-logo {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 35%;
    margin-top: 30px;
    margin-bottom: 30px;
  }
  .logo-name {
    color: white !important;
    left: px;
    font-family: 'Noto Serif SC', serif;
    font-weight: 400;
    font-size: 22px;
    letter-spacing: 3px;
  }
  .nav-item {
    list-style-type: none;
  }
  #nav {
    background-color: #1A284F;
    position: relative;
  }
  #side-nav {
    margin: 0;
    padding: 0;
    position: fixed;
    min-height: 765px;
    height: 100%;
    left: -40px;
    background: #fff;
    width: 120px;
    overflow: hidden;
    transition: width 0.2s linear;
    box-shadow: 0 20px 35px rgba(0, 0, 0, 0.1);
  }
  #admin-nav {
    background-color: #1A284F;
    position: relative;
    float: right;
    width: 100%;
  }
  #admin-side-nav {
    margin: 0;
    padding: 0;
    left: -40px;
    position: fixed;
    min-height: 800px;
    height: 110%;
    list-style-type: none;
    background: #fff;
    width: 290px;
    background-color: #fff;
    border-right: #1A284F solid 2px;
    box-shadow: 0 20px 35px rgba(0, 0, 0, 0.1);
  }
  #admin-side-nav-link {
    color: #1A284F;
    position: relative;
    bottom: side-nav height;
    font-size: 14px;
    display: table;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 30px;
    padding-left: 30px;
    transition: all 300ms ease-in-out;
  }
  #admin-side-nav-link:hover {
    color: white;
    background: #1A284F;
    border-radius: 40px 0 0 40px;
  }
  #nav-link {
    color: white !important;
  }
  #side-nav-link {
    color: #1A284F;
    position: relative;
    bottom: side-nav height;
    font-size: 14px;
    display: table;
    width: 300px;
    padding-top: 10px;
    padding-bottom: 30px;
    padding-left: 30px;
  }
  .fa {
    position: relative;
    width: 20px;
    height: 20px;
    top: 14px;
    font-size: 20px;
    text-align: center;
  }
  .nav-name {
    position: relative;
    top: 12px;
    margin-left: 33px;
    font-size: 16px;
  }
  .admin-nav-name {
    position: relative;
    top: 12px;
    margin-left: 20px;
    font-size: 16px;
  }
  #side-nav-link:hover {
    background: #eee;
  }
  #side-nav:hover {
    width: 320px;
    transition: all 0.5s ease;
  }
</style>
