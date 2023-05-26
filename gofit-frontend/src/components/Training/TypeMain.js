import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Container, Col, Row, Table, Button } from "react-bootstrap";

import './Training.css';
import CustomAxios from "../CustomAxios/CustomAxios";

const TypeMain = () => {
// const location = useLocation();
// location.state
// const params = useParams();
  const navigate = useNavigate();
  const trainingTypeClickHandler = (type) => {
    navigate("/training/type/" + type);   // const params = useParams();
    console.log(type);
  }
  const types = [
    {
      "type" : "Flexibility",
      "image" : "https://images.pexels.com/photos/6454058/pexels-photo-6454058.jpeg?cs=srgb&dl=pexels-marta-wave-6454058.jpg&fm=jpg",
      "description" : "Improved flexibility produces a wide range of physical benefits and can have a positive effect on your overall well-being."
    },
    {
      "type" : "Strength",
      "image" : "https://images.pexels.com/photos/3823063/pexels-photo-3823063.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
      "description" : "Strength training involves the performance of physical exercises that are designed to improve strength and endurance."
    },
        {
      "type" : "Cardio",
      "image" : "https://images.pexels.com/photos/2294403/pexels-photo-2294403.jpeg?cs=srgb&dl=pexels-li-sun-2294403.jpg&fm=jpg",
      "description" : "Regular cardio exercise can also help you lose weight, get better sleep, and reduce your risk for chronic disease."
    }, 
    // {
    //   "type" : "Weight",
    //   "image" : "https://images.pexels.com/photos/4498294/pexels-photo-4498294.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    //   "weight" : ""
    // },
    {
      "type" : "High intensity (HIIT)",
      "image" : "https://images.pexels.com/photos/6454071/pexels-photo-6454071.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
      "description" : "HIIT involves short bursts of intense exercise alternated with low intensity recovery periods."
    }
  ]

  return (
    <div className="container-fluid cardContainer mt-5 p-3 shadow-sm bg-body rounded">
      <h3 id="row" className="pb-2 pt-2">Training type</h3>
      <div className="row flex-row flex-nowrap card-group disable-scrollbars">
        {types !== null && types.map((type,index) => {
          return <div className="col-3" key={`${type}__${index}`} onClick={() => trainingTypeClickHandler(type.type)}>
            <div className="card cardsEdit">
              <img className="card-img-top cardImg" src={type.image} alt="card"></img>
              {/* <div className="card-img-overlay">
                <p className='card-text'>{training.name}</p>
              </div> */}
              <div className="card-body">
                <h5 className="card-title">{type.type}</h5>
                <p className="card-text">{type.description}</p>
              </div>
            </div>
          </div>
        })}
      </div>
    </div>
  )
}

export default TypeMain;