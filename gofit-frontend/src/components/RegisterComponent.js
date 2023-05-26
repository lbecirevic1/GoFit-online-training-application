import React, { Component, useState } from 'react'
import '../css/register.css'
import AxiosService from '../services/AxiosService';
import { useNavigate, Link } from 'react-router-dom';

const RegisterComponent = () => {
  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [success, setSuccess] = useState("");
  const[age, setAge] = useState('');
  
  let navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
        const res = await AxiosService.register(name, lastName, username, email, password, age);
        setSuccess(true);
        console.log(res);
        navigate('/login');

    } catch (error) {
        console.log(JSON.parse(localStorage.getItem("user")));
        console.log(error);
    }
  }

    return (

      <div className='customregister'>
        <div className='container'>
          <div className='row'>
            <h3 className='mb-4'>Create an account</h3>
          </div>
          <div className='row'>
            <div className='col'>
              <div className='row form-row p-4'>
                <input type="text" placeholder='Name' className='form-control p-4' value={name} onChange={(e) => setName(e.target.value)}/>
              </div>
              <div className='row form-row p-4'>
                <input type="email" placeholder='Email address' className='form-control p-4' value={email} onChange={(e) => setEmail(e.target.value)}/>
              </div>
              <div className='row form-row p-4'>
                <input type="text" placeholder='Username' className='form-control p-4' value={username} onChange={(e) => setUsername(e.target.value)}/>
              </div>
            </div>
            <div className='col'>
              <div className='row form-row p-4'>
                <input type="text" placeholder='Last name' className='form-control p-4' value={lastName} onChange={(e) => setLastName(e.target.value)}/>
              </div>
              <div className='row form-row p-4'>
                  <input type="password" placeholder='Password' className='form-control p-4' value={password} onChange={(e) => setPassword(e.target.value)}/>
              </div>           
              <div className='row form-row p-4'>
                <input type="number" className='form-control p-4' placeholder='Age' value={age} onChange={(e) => setAge(e.target.value)} />
              </div>
            </div>
            <div className='row form-row'>
              <button type="button" className='loginbtn mt-5 mb-5 ml-3 registerbtn' onClick={handleRegister}>Sign up</button>
            </div>
              <p>Already have an account? <Link className='loginlink' to="/login">Log in</Link></p>
          </div>
        </div>
      </div>
    )
  }

  export default RegisterComponent;