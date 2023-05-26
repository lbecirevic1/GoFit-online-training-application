import React, { Component, useState } from 'react'
import '../css/login.css'
import { useNavigate } from "react-router-dom";
import AuthService from '../services/AuthService';
import { Link } from 'react-router-dom';
import jwt_decode from "jwt-decode";
import CustomAxios from '../components/CustomAxios/CustomAxios'

const LoginComp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
        const res = await AuthService.login(email, password);
        setSuccess(true);

        const accessToken = res?.data?.token;
        localStorage.setItem("accessToken", accessToken);

        const decodedToken = jwt_decode(accessToken);
        const roles = decodedToken.role;
        localStorage.setItem("roles", roles);
        localStorage.setItem("usersName", decodedToken.name);
        localStorage.setItem("email", decodedToken.sub)
        console.log(res);
        console.log(localStorage.getItem("roles"));
        if (localStorage.getItem("roles") === "ROLE_ADMIN") {
          navigate("/adminDashboard");
        } else if (localStorage.getItem("roles") === "ROLE_USER") {
          navigate("/training");
        }

    } catch (error) {
        console.log(JSON.parse(localStorage.getItem("user")));
        console.log(error);
    }
  }

    return (
      <div className='customlogin'>
        <div className='container'>
          <div className='row'>
            <div className='col'>
              <h3>Sign into your account</h3>
              <div className='row form-row p-4'>
              <input type="email" placeholder='Email' className='form-control p-4' value={email} onChange={(e) => setEmail(e.target.value)}/>
              </div>
              <div className='row form-row p-4'>
                <input type="password" placeholder='Password' className='form-control p-4' value={password} onChange={(e) => setPassword(e.target.value)}/>
              </div>
              <div className='row form-row'>
                <button type="button" className='loginbtn mt-3 mb-5 ml-3' onClick={handleLogin}>Login</button>
              </div>
              {/* <Link className="loginlink" to="/resetLink">Forgot password?</Link> */}
              <p>Dont have an account? <Link className='loginlink' to="/register">Register</Link></p>
            </div>
            <div className='col'>

            </div>
          </div>
        </div>
      </div>
    )
  }

export default LoginComp;
