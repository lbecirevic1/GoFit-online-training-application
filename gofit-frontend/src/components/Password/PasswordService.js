import axios from 'axios'

const getResetLink = (email) => axios.post("http://localhost:8088/user/resetLink", { email });

const changePassword = (email, password) => axios.put("http://localhost:8088/user/changePassword")

const AxiosService = {
  getResetLink,
  changePassword
};

export default AxiosService;