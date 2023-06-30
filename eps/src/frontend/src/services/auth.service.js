import axios from 'axios';
import jwt_decode from "jwt-decode";

const API_URL = '/api/v1/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'authenticate', {
        email: user.email,
        password: user.password
      })
      .then(response => {
        var token = response.data.token; // JWT token
        console.log(token);
        var decoded = jwt_decode(token); // Decoded JWT token
        console.log(decoded);
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(decoded)); //JSON.stringify(response.data));
        }
        return { username: decoded.username,
                 email: decoded.email,
                 studentId: decoded.studentId,
                 department: decoded.department,
                 role: decoded.role };
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'register', {
      username: user.username,
      email: user.email,
      password: user.password,
      studentId: user.studentId,
      department: user.department
    });
  }
}

export default new AuthService();
