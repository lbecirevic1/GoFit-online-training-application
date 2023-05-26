import React, {Component, useState} from 'react'
import '../css/training.css'
import { useNavigate, Link } from 'react-router-dom';
const TrainingComponent = () =>{

    let navigate=useNavigate();

    const trainingHandler = () => {
        navigate('/trainingList');
    }

    return(

        <div className='training2'>

            <div className='container'>
                <div className='textTraining2'>
                    <h1 style={{fontSize:"80px"}}>Summer challenge
                    </h1>
                </div>
                <div className='buttondiv'>
                    <button type="button" className='startbtn mt-5 mb-5 ml-3 startbtn' onClick={trainingHandler}>Start</button>
                </div>
            </div>
        </div>

    ) }

export default TrainingComponent;
