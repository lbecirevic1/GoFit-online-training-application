import axios from 'axios'

const resetPassword = (email, password) => axios.patch("http://localhost:8088/user/password/reset", { email, password });
const register = (name, lastName, username, email, password) => axios.post("http://localhost:8088/user/register", { name, lastName, username, email, password });


const AxiosService = {
  resetPassword,
  register
};

export default AxiosService;