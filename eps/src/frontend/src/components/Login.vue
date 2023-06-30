<template>
  <section class="section">
    <section class="bg"></section>
        
    <div class="form-box">
      <Form @submit="handleLogin" :validation-schema="schema">
        <h2 class="login-header">登录</h2>
        <img src="login_page.jpg" width="60px" height="40px" style="position: fixed;">
        <div class="inputbox">
          <ion-icon name="mail-outline"></ion-icon>
          <Field name="email" type="email" class="form-control" placeholder=" "/>
          <label for="">邮箱</label>
          <ErrorMessage name="email" class="error-feedback" />
        </div>
        <div class="inputbox">
          <ion-icon name="lock-closed-outline"></ion-icon>
          <Field name="password" type="password" class="form-control" placeholder=" "/>
          <label for="">密码</label>
          <ErrorMessage name="password" class="error-feedback" />
        </div>

        <button class="login-button">登录</button>

        <div style="text-align: center; margin:20px;">
          <a class="register" href="/register">注册</a>
        </div>

        <!-- <div class="inputbox">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span
              v-show="loading"
              class="spinner-border spinner-border-sm"
            ></span>
            <span>Login</span>
          </button>
        </div> -->

        <!-- <div class="inputbox">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div> -->
      </Form>
    </div>
  </section>
</template>
  
<script>
  import { Form, Field, ErrorMessage } from "vee-validate";
  import * as yup from "yup";
  
  export default {
    name: "Login",
    components: {
      Form,
      Field,
      ErrorMessage,
    },
    mounted() {
      // Script to import ion icons
      const ionIcons = document.createElement("script");
      ionIcons.setAttribute(
        "type",
        "module"
      );
      ionIcons.setAttribute(
        "src",
        "https://unpkg.com/ionicons@5.0.0/dist/ionicons/ionicons.esm.js"
      );
      document.head.appendChild(ionIcons);
      
      // Blurry loading
      const bg = document.querySelector('body')

      let load = 0

      let int = setInterval(blurring, 30)

      function blurring() {
        load++;

        if(load > 99) {
          clearInterval(int)
        }

        bg.style.filter = `blur(${scale(load, 0, 50, 20, 0)}px)`
        }

        const scale = (num, in_min, in_max, out_min, out_max) => {
          return ((num - in_min) * (out_max - out_min)) / (in_max - in_min) + out_min
        }
    },
    data() {
      const schema = yup.object().shape({
        // email: yup.string().required("Email is required!"),
        // password: yup.string().required("Password is required!"),
      });
  
      return {
        loading: false,
        message: "",
        schema,
      };
    },
    computed: {
      loggedIn() {
        return this.$store.state.auth.status.loggedIn;
      },
      currentUser() {
        return this.$store.state.auth.user;
    },
    },
    created() {
      if (this.loggedIn) {
        this.$router.push("/home");
      }
    },
    methods: {
      handleLogin(user) {
        this.loading = true;
  
        this.$store.dispatch("auth/login", user).then(
          () => {
            if (this.currentUser.role == "ADMIN")
              this.$router.push( { path: "/daily-report-admin" } );
            else
            this.$router.push( { path: "/home" } );
          },
          (error) => {
            this.loading = false;
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
          }
        );
      },
    },
  };  

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');

/* *{
    margin: 0;
    padding: 0;
    font-family: 'poppins',sans-serif;
} */
.bg {
    background: url('/src/img/login_page2_new.png') no-repeat center center/cover;
    position: absolute;
    top: -10px;
    left: -10px;
    width: calc(100vw + 10px);
    height: calc(100vh + 10px);
    z-index: -1;
    filter: blur(0px);
}
.section{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    width: 100%;   
}
.form-box{
    position: relative;
    width: 400px;
    height: 450px;
    background: transparent;
    border: 2px solid rgba(255,255,255,0.5);
    border-radius: 20px;
    backdrop-filter: blur(15px);
    display: flex;
    justify-content: center;
    align-items: center;
}
.login-header{
    font-size: 2em;
    color: #fff;
    text-align: center;
}
.inputbox{
    position: relative;
    margin: 30px 0;
    width: 310px;
    border-bottom: 2px solid #fff;
}
.inputbox label{
    position: absolute;
    top: 50%;
    left: 5px;
    transform: translateY(-50%);
    color: #fff;
    font-size: 1em;
    pointer-events: none;
    transition: .5s;
}
.form-control:focus ~ label,
.form-control:not(:placeholder-shown) ~ label{
top: -5px;
right: 100px;
}
.inputbox .form-control{
    width: 100%;
    height: 50px;
    background: transparent !important;
    border: none;
    outline: none;
    font-size: 1em;
    padding:0 35px 0 5px;
    color: #fff !important;
}
.form-control:focus, .form-control:active{
    background: transparent !important;
    border: none !important;
    outline: none !important;
    box-shadow: none !important;
}
.inputbox ion-icon{
    position: absolute;
    right: 8px;
    color: #fff;
    font-size: 1.2em;
    top: 20px;
}
.login-button{
    width: 100%;
    height: 40px;
    border-radius: 40px !important;
    background: #fff;
    border: none;
    outline: none;
    cursor: pointer;
    font-size: 1em;
    font-weight: 600;
}


.register{
    font-size: 1em;
    font-weight: 600;
    color: #fff;
    text-align: center;
    margin-bottom: 25px;
    text-decoration: underline;
}
</style>