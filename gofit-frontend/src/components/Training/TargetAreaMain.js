import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Container, Col, Row, Table, Button } from "react-bootstrap";

import './Training.css';
import CustomAxios from "../CustomAxios/CustomAxios";

const TargetAreaMain = () => {
// navigate("/training/targetArea/" + targetArea, {state: targetArea});
// const location = useLocation();
// location.state

  const navigate = useNavigate();
  const trainingTargetAreaClickHandler = (targetArea) => {
    navigate("/training/targetArea/" + targetArea);   // const params = useParams();
    console.log(targetArea);
  }
  const targets = [
    {
      "targetArea" : "Abs",
      "image" : "https://images.pexels.com/photos/7674486/pexels-photo-7674486.jpeg?cs=srgb&dl=pexels-cottonbro-7674486.jpg&fm=jpg",
      "description" : "Start sculpting your abs with hundreds of super effective abs workouts."
    },
    {
      "targetArea" : "Arms",
      "image" : "https://images.pexels.com/photos/11433027/pexels-photo-11433027.jpeg?cs=srgb&dl=pexels-marius-aholou-11433027.jpg&fm=jpg",
      "description" : "Arm exercises for sculpting your shoulders, biceps and triceps"
    }, 
    {
      "targetArea" : "Legs", 
      "image" : "https://images.pexels.com/photos/6339655/pexels-photo-6339655.jpeg?cs=srgb&dl=pexels-pavel-danilyuk-6339655.jpg&fm=jpg",
      "description" : "Leg workouts focuses on the quads, hamstrings, glutes and core."
    },
    {
      "targetArea" : "Full body",
      "image" : "https://images.pexels.com/photos/4498607/pexels-photo-4498607.jpeg?cs=srgb&dl=pexels-karolina-grabowska-4498607.jpg&fm=jpg",
      "description" : "Compound exercises that fire up multiple muscle groups at once."
    },
    {
      "targetArea" : "Back",
      "image" : "https://images.pexels.com/photos/1865131/pexels-photo-1865131.jpeg?cs=srgb&dl=pexels-murilo-botelho-1865131.jpg&fm=jpg",
      "description" : "Compound back exercises to train all of your major back muscles."
    }
  ]

  return (
    <div className="container-fluid cardContainer mt-5 p-3 shadow-sm bg-body rounded">
      <h3 id="row" className="pb-2 pt-2">Target area</h3>
      <div className="row flex-row flex-nowrap card-group disable-scrollbars">
        {targets !== null && targets.map((target, index) => {
          return <div className="col-3" key={`${index}__${target.targetArea}`} onClick={() => trainingTargetAreaClickHandler(target.targetArea)}>
            <div className="card cardsEdit">
              <img className="card-img-top cardImg" src={target.image} alt="card"></img>
              {/* <div className="card-img-overlay">
                <p className='card-text'>{training.name}</p>
              </div> */}
              <div className="card-body">
                <h5 className="card-title">{target.targetArea}</h5>
                <p className="card-text">{target.description}</p>
              </div>
            </div>
          </div>
        })}
      </div>
    </div>
  )
}

export default TargetAreaMain;