<template>
  <section class="section">
    <section class="bg"></section>
    <div class="form-box">
      <Form @submit="handleRegister" :validation-schema="schema">
        <h2 class="login-header">注册</h2>
        <div v-if="!successful">
          <div class="inputbox">
            <ion-icon name="person-outline"></ion-icon>
            <Field name="username" type="text" class="form-control" placeholder=" "/>
            <label for="username">姓名</label>
            <!-- <ErrorMessage name="username" class="error-feedback" /> -->
          </div>
          <div class="inputbox">
            <ion-icon name="information-circle-outline"></ion-icon>
            <Field name="studentId" type="text" class="form-control" placeholder=" "/>
            <label for="studentId">学工号</label>
            <!-- <ErrorMessage name="studentId" class="error-feedback" /> -->
          </div>
          <div class="inputbox">
            <ion-icon name="school-outline"></ion-icon>
            <Field name="department" type="text" class="form-control" placeholder=" "/>
            <label for="department">学院或部门</label>
            <ErrorMessage name="department" class="error-feedback" />
          </div>
          <div class="inputbox">
            <ion-icon name="mail-outline"></ion-icon>
            <Field name="email" type="email" class="form-control" placeholder=" "/>
            <label for="email">邮箱</label>
            <!-- <ErrorMessage name="email" class="error-feedback" /> -->
          </div>
          <div class="inputbox">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <Field name="password" type="password" class="form-control" placeholder=" "/>
            <label for="password">密码</label>
            <!-- <ErrorMessage name="password" class="error-feedback" /> -->
          </div>

          <button class="login-button" :disabled="loading">
            <span
              v-show="loading"
              class="spinner-border spinner-border-sm"
            ></span>
            注册
          </button>
        </div>
      </Form>

      <!-- <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div> -->
    </div>
  </section>
</template>
  
<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      // username: yup
      //   .string()
      //   .required("Username is required!")
      //   .min(3, "Must be at least 3 characters!")
      //   .max(20, "Must be maximum 20 characters!"),
      // email: yup
      //   .string()
      //   .required("Email is required!")
      //   .email("Email is invalid!")
      //   .max(50, "Must be maximum 50 characters!"),
      // password: yup
      //   .string()
      //   .required("Password is required!")
      //   .min(6, "Must be at least 6 characters!")
      //   .max(40, "Must be maximum 40 characters!"),
    });

    return {
      successful: false,
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
    
    // Script to import ion icons
    const recaptchaScript = document.createElement("script");
      recaptchaScript.setAttribute(
        "type",
        "module"
      );
      recaptchaScript.setAttribute(
        "src",
        "https://unpkg.com/ionicons@5.0.0/dist/ionicons/ionicons.esm.js"
      );
      document.head.appendChild(recaptchaScript);
  },
  methods: {
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
          alert("注册成功");
          this.$router.push("/login");
        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
  },
};
</script>
  
<style scoped>
  .bg{
    background: url('/src/img/login_page2_new.png') no-repeat center center/cover;
    position: absolute;
    top: -10px;
    left: -10px;
    width: calc(100vw + 10px);
    height: calc(100vh + 10px);
    z-index: -1;
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
      width: 500px;
      height: 650px;
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
      width: 340px;
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
</style>
