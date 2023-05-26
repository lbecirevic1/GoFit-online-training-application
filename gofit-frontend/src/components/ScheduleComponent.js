import React, {Component, useState} from 'react'
import '../css/schedule.css'
import { useNavigate, Link } from 'react-router-dom';
import ScheduleService from '../services/ScheduleService';
const ScheduleComponent = () =>{

    let navigate=useNavigate();

    const scheduleHandler = async  () => {
        ScheduleService.createSchedule().then((res)=>{
            console.log('ress',res)
            navigate('/calendar');
        })
    }

    return(

        <div className='schedule2'>

            <div className='container'>
                <div className='textSchedule2'>
                    <h1 style={{fontSize:"80px"}}>Plan your time!
                    </h1>
                </div>
                <div className='buttondiv'>
                    <button type="button" className='calendarbtn mb-5 ml-3 calendarbtn' onClick={scheduleHandler}>Go to my planer!</button>
                </div>
            </div>
        </div>

    ) }

export default ScheduleComponent;
