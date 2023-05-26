import React, {Component, useState} from 'react'
import '../../css/DietPlan/dietplan.css'
import { useNavigate, Link } from 'react-router-dom';
const DietPlanComponent = () =>{

    let navigate=useNavigate();

    const dietPlanHandler = () => {
        navigate('/test');
    }

    return(

        <div className='dietplan'>

            <div className='container'>
            <div className='text'>
                <h3>Your personalized meal plan is set up and ready to enhance your <br/>
                fitness program so you can reach your goal even faster.
                </h3>
            </div>
<div className='buttondiv'>
                        <button type="button" className='testbtn mt-5 mb-5 ml-3 testbtn' onClick={dietPlanHandler}>Take a test!</button>
</div>
                </div>
        </div>

    ) }

export default DietPlanComponent;
