import React, { Component, useState } from 'react'
import PasswordService from './PasswordService';
import styles from './password.css';
import { useNavigate } from "react-router-dom";

const ChangePassword = () => {
  const [email, setEmail] = useState("");
  const [success, setSuccess] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const changePassword = async (e) => {
    e.preventDefault();
    try {
        const res = await PasswordService.changePassword(password, confirmPassword);
        setSuccess({ type: 'success'});
    } catch (error) {
      console.log(error);
      setSuccess({ type: "error"});
    }
  }

    return (
      <div className='custompassword'>
        <div className='container'>
          <div className='row'>
            <div className='col'>
              <h3>Reset password</h3>
              <div className='row form-row p-4'>
                <input type="password" placeholder='Password' className='form-control p-4' value={password} onChange={(e) => setPassword(e.target.value)}/>
              </div>
              <div className='row form-row p-4'>
                <input type="password" placeholder='Confirm password' className='form-control p-4' value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}/>
              </div>
              <div className='row form-row'>
                <button type="button" className='passwordbtn mt-3 mb-5 ml-3' onClick={changePassword}>Submit</button>
                {success?.type === 'success' && <p>Password changed successfully.</p>}
                {success?.type == "error" && <p>Invalid email address</p>}
              </div>
            </div>
            <div className='col'>
            </div>
          </div>
        </div>
      </div>
    )
  }

export default ChangePassword;
