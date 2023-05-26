import React, {Component, useState} from 'react'
import '../css/dashboard.css'
import { useNavigate, Link } from 'react-router-dom';
import dash1img from '../images/dash1.jpg'
import dash2img from '../images/dash2.jpg'
import dash3img from '../images/dash3.jpg'
import dash4img from '../images/dash4.jpg'
import dash5img from '../images/dash13.jpg'
import dash6img from '../images/dash14.jpg'
import dash7img from '../images/dash15.jpg'
import dash8img from '../images/dash16.jpg'
import dash9img from '../images/dash17.jpg'
const DashboardComponent = () =>{

    let navigate=useNavigate();

    const dietPlanHandler = () => {
        navigate('/dietplan');
    }

    const TrainingHandler = () => {
        navigate('/training')
    }

    const ScheduleHandler = ()=>{
        navigate('/home')
    }

    const MapHandler = () =>{
        navigate('/map')
    }

    return(

        <div className='dashboard'>

            <div className='container'>
                <div className='trainings'>
                <div className='textTraining'>
                    <h4>Explore trainings!</h4>
                    <p>Here are your last watched and saved trainings. <br/>
                        Be fit with GoFit!</p>
                </div>
                <div className='pics'>
                    <img style={{padding:10,width:"30%",height:"40%",alignContent:"center"}}
                         src={dash2img} alt="dash2" onClick={TrainingHandler}/>

                    <img style={{padding:10,width:"30%",height:"40%",alignContent:"center"}}
                         src={dash1img} alt="dash3" onClick={TrainingHandler}/>
                    <img style={{padding:10,width:"30%",height:"40%",alignContent:"center"}}
                         src={dash4img} alt="dash3" onClick={TrainingHandler}/>
                </div>
             <div className='textTraining'>
                 <p>Here is the link to your last watched video! <br/> </p>
                 <div className='buttondiv'>
                     <button type="button" className='videobtn mt-5 mb-5 ml-3 videobtn' onClick={TrainingHandler}>Last video</button>
                 </div>
             </div>
            </div>
        </div>

            <div className='container'>
                <div className='dietplans'>
                    <div className='textDietPlan'>
                        <h4>DietPlan</h4>
                        <p>Every day is a healthy-meal-day! <br/>
                            Check out our delicious ideas for your menu today!<br/>
                            Stay healthy with GoFit.</p>
                    </div>
                    <div className='pics'>
                        <div style={{flex:1,flexDirection:"row"}}>
                            <img style={{padding:10,width:"100%",height:"80%"}}
                                 src={dash5img} alt="dash2" onClick={dietPlanHandler}/>
                            <p style={{flexDirection:"row",margin:10}}>Breakfast</p>
                        </div>
                        <div style={{flex:1,flexDirection:"row"}}>
                            <img style={{padding:10,width:"100%",height:"80%"}}
                                 src={dash7img} alt="dash3" onClick={dietPlanHandler}/>
                            <p style={{flexDirection:"row",margin:10}}>Smoothie of the day!</p>
                        </div>
                        <div style={{flex:1,flexDirection:"row"}}>
                            <img style={{padding:10,width:"100%",height:"80%"}}
                                 src={dash6img} alt="dash3" onClick={dietPlanHandler}/>
                            <p style={{flexDirection:"row",margin:10}}>Dinner</p>
                        </div>
                    </div>

                </div>
            </div>

            <div className='container'>
                <div className='schedule'>
                    <div className='textSchedule'>
                        <h4>A goal without a plan is just a wish!</h4>
                        <p>Make yourself a priority! <br/>
                            Plan your next training as soon as possible!</p>
                    </div>
                    <div className='pics'>
                        <div style={{flex:1,flexDirection:"row"}}>
                            <img style={{padding:10,width:"80%",height:"80%",left:"0%",alignContent:"center",position:"relative"}}
                                 src={dash9img} alt="dash2"/>
                        </div>
                    </div>
                    <div className='buttondiv'>
                        <button type="button" className='schedulebtn mt-5 mb-5 ml-3 schedulebtn' onClick={ScheduleHandler}>Go to my schedule</button>

                        <button type="button" className='mapbtn mt-5 mb-5 ml-3 mapbtn' onClick={MapHandler}>Go to map</button>
                    </div>

                </div>
            </div>

        </div>
    ) }

export default DashboardComponent;
