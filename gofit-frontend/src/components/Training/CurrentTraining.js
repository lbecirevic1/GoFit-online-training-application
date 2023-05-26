import { Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { Row, Table, Button } from 'react-bootstrap'

import CustomAxios from "../CustomAxios/CustomAxios";
import Training from "../../pages/Training";
import VideoModal from "./Modal/VideoModal";
import VoteModal from "./Modal/VoteModal";

const CurrentTraining = () => {
  const params = useParams();
  const [training, setTraining] = useState('');
  const [exercises, setExercises] = useState(null);

  const [openModal, setOpenModal] = useState(false);
  const [vote, setVote] = useState(false);
  const [openVoteModal, setOpenVoteModal] = useState(false);
  const [usersVote, setUsersVote] = useState('');

  
  useEffect(() => {
    console.log(vote);
    const fetchTraining = async () => {
      const response = await CustomAxios.get('http://localhost:8088/training/training/' + params.id);
      setTraining(response?.data);
      console.log(response?.data);
    } 

    const fetchExercises = async () => {
      const response = await CustomAxios.get('http://localhost:8088/training/exercisesForTraining/' + params.id);
      setExercises(response?.data);
      console.log(response?.data);
    } 

    fetchTraining().catch(error => {
      console.log(error);
    });

    fetchExercises().catch(error => {
      console.log(error);
    });
  }, []);



  return (
    <Container className="mt-5 pt-4 pb-5 mb-4">
      <Row className="mb-3">
        <h2>{training.name}</h2>
      </Row>
      <Row>
        <p>{training.description}</p>
      </Row>
      <Row className="mb-2 mt-3">
        <h3>Exercises</h3>
      </Row>
      {exercises !== null && exercises.map((exercise) => {
        return <div className="card cardCurrentTraining mt-3" key={exercise.id}>
          <div className="row g-0">
            <div className="col-md-4">
              <img src={exercise.image} className="img-fluid rounded-start cardImgType" alt={exercise.name}></img>
            </div>
            <div className="col-md-8">
                <div className="card-body">
                  <h4 className="card-title">{exercise.name}</h4>
                  <p className="card-text">{exercise.description}</p>
                  <p className="card-text">Duration: {exercise.duration} seconds.</p>
                </div>
            </div>
          </div>
        </div>
      })}
      <Row className="mt-4 d-flex">
          <Button className='btn text-center finishTrainingBtn ml-auto' onClick={() => {setOpenModal(true); console.log(vote)}}>Start training</Button>
      </Row>
      {openModal &&  <VideoModal setOpenModal={setOpenModal} setVote={setVote} videoSource={training.videoLink}/>}
      {vote && <VoteModal setOpenVoteModal={setOpenVoteModal} setVote={setVote} setUsersVote={setUsersVote} usersVote={usersVote} finishedTraining={training}/>}
    </Container>
  )
}

export default CurrentTraining;