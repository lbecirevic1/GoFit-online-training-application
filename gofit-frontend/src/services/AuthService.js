import axios from 'axios'

const login = (email, password) => axios.post("http://localhost:8088/user/login", { email, password });

const logout = () => {
  localStorage.removeItem("user");
}

const getCurrentUser =  () => {
  return JSON.parse(localStorage.getItem("user"));
}

const AuthService = {
  login,
  logout,
  getCurrentUser
};

export default AuthService;

