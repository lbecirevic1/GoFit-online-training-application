import axios from 'axios'

const register = (name, lastName, username, email, password) => axios.post("http://localhost:8088/user/register", { name, lastName, username, email, password });


const RegisterService = {
  register
};

export default RegisterService;