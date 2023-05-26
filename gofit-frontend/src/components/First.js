import React, { Component } from 'react'
import '../css/first.css'
import lungesimg from '../images/lunges.png'
import pushupsbimg from '../images/pushupsb.png'
import {Link} from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { Nav } from 'react-bootstrap';

import '../css/home.css'
export default class First extends Component {
  render() {
    return (
      <>
        <div className='custom'>
          <div className='container'>
              <div className='row firstrow'>
                {/* <div className='col'> */}
                  <div className='tocenter'>
                    <div className='row pl-4 pb-4'>
                      <h1 className='titleText'>Free fitness app</h1>
                    </div>
                    <div className='row pl-4 pb-4'>
                      <p className='homeText'>You want to lose weight, gain muscle mass, <br/> improve your flexibility or
                      increase endurance? <br/>
                      Then GoFit is for you!</p>
                    </div>
                  </div>
                  <div className='row pl-4'>
                    <Button type='button' className='registerButton pr-4 pl-4'><Nav.Link as={Link} to="/register">Go Fit</Nav.Link></Button>
                  </div>
                {/* </div> */}
                {/* <div className='col'>
                  <img src={lungesimg} alt="lunges"/>
                </div> */}
              </div>
          </div>
          <svg className='top' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 300"><path fill="#FAD1F0" fill-opacity="1" d="M0,320L120,277.3C240,235,480,149,720,144C960,139,1200,213,1320,250.7L1440,288L1440,320L1320,320C1200,320,960,320,720,320C480,320,240,320,120,320L0,320Z"></path></svg>
       {/* <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FAD1F0" fill-opacity="1" d="M0,256L120,234.7C240,213,480,171,720,176C960,181,1200,235,1320,261.3L1440,288L1440,0L1320,0C1200,0,960,0,720,0C480,0,240,0,120,0L0,0Z"></path></svg> */}
        </div>
        {/* <div className='custom2 backgroundcolor'>
          <div className='container'>
              <div className='row firstrow backgroundcolor'>
                <div className='col'>
                  <img src={pushupsbimg} alt="pushups"/>
                </div>
                <div className='col'>
                  <div className='tocenter'>
                    <div className='row pl-4 pb-4 backgroundcolor'>
                      <h4>Take control</h4>
                    </div>
                    <div className='row pl-4 pb-4 backgroundcolor'>
                      <p>Take complete control of your training <br/> through hundreds of workouts available online
                      <br/> and train anytime and anywhere. </p>
                    </div>
                  </div>
                </div>
              </div>
          </div>
        </div> */}
      </>
    )
  }
}
