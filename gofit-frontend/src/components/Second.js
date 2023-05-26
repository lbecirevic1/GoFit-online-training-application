import React, { Component } from 'react'
import healthyimg from '../images/healthy1.png'
import mobileappimg from "../images/mobileapp.png"
import "../css/second.css"
import { useNavigate } from "react-router-dom";


const Second = () => {
    let navigate = useNavigate(); 
    const routeChange = () => { 
    let path = '/register'; 
    navigate(path);
  }
    return (
      <>
          <div className='custom3'>
            <div className='container'>
                <div className='row'>
                  <div className='col'>
                    <div className='tocenter'>
                      <div className='row pl-4 pb-4'>
                      <h4>Healthy meal plan</h4>
                      </div>
                      <div className='row pl-4 pb-4'>
                        <p>Proper nutrition is the key for getting better results. <br/>
                        After creating your GoFit profile you will get <br/> free meal plan
                        from our nutritionists. <br/> All you have to do is take quick test
                        which will help <br/> our nutritionists in designing the best healthy <br/> meal plan for you. </p>
                      </div>
                    </div>
                  </div>
                  <div className='col'>
                    <img src={healthyimg} alt="Girl eating healthy"/>
                  </div>
                </div>
            </div>

          </div>
          <div className='custom4 backgroundcolor'>
            <div className='container backgroundcolor'>
                <div className='row backgroundcolor'>
                 <div className='col backgroundcolor'>
                    <img src={mobileappimg} alt="Girl eating healthy"/>
                  </div>
                  <div className='col'>
                    <div className='tocenter4'>
                      <div className='row pl-4 pb-4 backgroundcolor'>
                      </div>
                      <div className='row pl-4 pb-4 backgroundcolor'>
                        <h3>Web and mobile app <br/> available!</h3>
                      </div>
                      <div className='row pl-4 pb-4 backgroundcolor'>
                        <button type='button' className='joinusbtn pr-4 pl-4' onClick={routeChange}>Join us</button>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
            <svg className='bottom' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="white" fill-opacity="1" d="M0,64L120,101.3C240,139,480,213,720,213.3C960,213,1200,139,1320,101.3L1440,64L1440,320L1320,320C1200,320,960,320,720,320C480,320,240,320,120,320L0,320Z"></path></svg>            
            {/* <p id="gofitfooter">GoFit</p> */}
          </div>
        </>
    )
}

export default Second;
