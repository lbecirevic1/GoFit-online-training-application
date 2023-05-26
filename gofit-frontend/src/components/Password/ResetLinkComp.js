import React, { Component, useState } from 'react'
import PasswordService from './PasswordService';
import styles from './password.css';
import { useNavigate } from "react-router-dom";

const ResetLinkComp = () => {
  const [email, setEmail] = useState("");
  const [success, setSuccess] = useState("");

  const getResetLink = async (e) => {
    e.preventDefault();
    try {
        const res = await PasswordService.getResetLink(email);
        console.log(res.data);
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
              <br/>
              <h4>To get reset link, please enter email associated with your account</h4>
              <div className='row form-row p-4'>
              <input type="email" placeholder='Email' className='form-control p-4' value={email} onChange={(e) => setEmail(e.target.value)}/>
              </div>
              <div className='row form-row'>
                <button type="button" className='passwordbtn mt-3 mb-5 ml-3' onClick={getResetLink}>Get link</button>
                {success?.type === 'success' && <p>Successfully sent. Check your inbox.</p>}
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

export default ResetLinkComp;
