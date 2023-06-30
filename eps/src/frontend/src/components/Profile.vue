<template>
  <!-- <header class="jumbotron">
    <h3>
      <strong>
        {{currentUser.username}}
      </strong> Profile
    </h3>
  </header> -->
  <img
    id="profile-img"
    src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
    class="profile-img-card"
    style="padding-top: 20px;"
  />
  <div class="container">
    <!-- <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p> -->
    <strong id="id">学工号:</strong>
    <label for="id">{{currentUser.studentId}}</label>

    <strong id="name">姓名:</strong>
    <label for="name">{{currentUser.username}}</label>

    <!-- <strong id="phone-number">手机号码:</strong>
    <label for="phone-number">1234</label> -->

    <strong id="email">邮箱地址:</strong>
    <label for="email">{{currentUser.email}}</label>

    <strong id="department">学院或部门:</strong>
    <label for="department">{{currentUser.department}}</label>
    <!-- <strong>Authorities:</strong>
    <ul>
      <li v-for="role in currentUser.roles" :key="role">{{role}}</li>
    </ul> -->
  </div>
</template>
  
<script>
  export default {
    name: 'Profile',
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
    },
    mounted() {
      if (!this.currentUser) {
        this.$router.push('/login');
      }
    }
  };
</script>

<style scoped>
  .profile-img-card{
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 20%;
    border-radius: 50%;
  }
  .container{
    display: grid;
    height: 400px;
    align-content: space-evenly;
    grid-template-columns: 150px 200px;
    font-size: 18px;
  }
</style>