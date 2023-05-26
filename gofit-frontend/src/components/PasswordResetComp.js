import React, { Component, useState } from 'react'
import '../css/passwordReset.css'
import { useNavigate } from "react-router-dom";
import AxiosService from '../services/AxiosService';

const PasswordResetComp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [success, setSuccess] = useState("");

  const handlePasswordReset = async (e) => {
    e.preventDefault();
    try {
        const res = await AxiosService.resetPassword(email, password);
    } catch (error) {
      console.log(error);
    }
  }

    return (
      <div className='custompassword'>
        <div className='container'>
          <div className='row'>
            <div className='col'>
              <h3>Reset password</h3>
              <div className='row form-row p-4'>
              <input type="email" placeholder='Enter email associated with your account' className='form-control p-4' value={email} onChange={(e) => setEmail(e.target.value)}/>
              </div>
              <div className='row form-row p-4'>
                <input type="password" placeholder='Password' className='form-control p-4' value={password} onChange={(e) => setPassword(e.target.value)}/>
              </div>
              <div className='row form-row'>
                <button type="button" className='passwordbtn mt-3 mb-5 ml-3' onClick={handlePasswordReset}>Reset</button>
              </div>
            </div>
            <div className='col'>

            </div>
          </div>
        </div>
      </div>
    )
  }

export default PasswordResetComp;
