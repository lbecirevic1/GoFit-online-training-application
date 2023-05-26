import { useState } from "react";
import { Row, Col, Button } from "react-bootstrap";
import CustomAxios from "../../CustomAxios/CustomAxios";
import "./Modal.css";

const VoteModal = ({setOpenVoteModal, setVote, setUsersVote, usersVote, finishedTraining}) => {

  const [data, setData] = useState({
    email : "",
    trainingId : "",
    vote : ""
  });

  const saveInTrainingHistory = () => {
    const email = localStorage.getItem("email");
    setData(data.email = email);
    setData(data.trainingId = finishedTraining.id);
    console.log(data.trainingId);
    console.log(data.vote);
    console.log(data.email);
    const response = CustomAxios.post("http://localhost:8088/training/trainingHistory", data);
    console.log(response?.data);
    console.log("saved history");
  }

  return (
    <div className="showVideo bgpr d-flex">
      <div className="modalVoteContainer mx-auto my-auto">
        <div className="body">
          <h1 className="mt-2 mb-4">Rate training</h1>
          <Row className="mt-5">
            <Col className="col-md-4 voteCol d-flex justify-content-center">
                <button type="button" class="voteBtn btn-default btn-circle btn-xl" onClick={() => {
                  setOpenVoteModal(false); 
                  setVote(false); 
                  setUsersVote(1);
                  setData(data.vote = 1)
                  saveInTrainingHistory();
                  }}>1</button>
            </Col>
            <Col className="col-md-4 voteCol d-flex justify-content-center">
                <button type="button" class="voteBtn btn-default btn-circle btn-xl" onClick={() => {
                  setOpenVoteModal(false); 
                  setVote(false); 
                  setUsersVote(2)
                  setData(data.vote = 2)
                  saveInTrainingHistory();
                  }}>2</button>
            </Col>
            <Col className="col-md-4 voteCol d-flex justify-content-center">
                <button type="button" class="voteBtn btn-default btn-circle btn-xl" onClick={() => {
                  setOpenVoteModal(false); 
                  setVote(false); 
                  setUsersVote(3);
                  setData(data.vote = 3)
                  saveInTrainingHistory();
                  }}>3</button>
            </Col>
          </Row>
        </div>
        {/* <div className="d-flex  mt-3">
        <div className="footer mx-auto">
          <button onClick={() => {setOpenVoteModal(false);}} className="btn voteBtn mr-2"> Vote </button>
        </div>
        </div> */}

      </div>
    </div>
  
  )
}

export default VoteModal;