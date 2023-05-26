import { useEffect } from "react";
import { Container, Row } from "react-bootstrap";
import { useState } from "react";
import CustomAxios from "./CustomAxios/CustomAxios";
import { Card, Button} from "react-bootstrap";
import { CardBody } from "reactstrap";

const Profile = () => {
  const [user, setUser] = useState(null);
  const [email, setEmail] = useState('');
  const [trainingHistory, setTrainingHistory] = useState(null);

  const [vote, setVote] = useState('');

  useEffect(() => {
    setUser(localStorage.getItem("usersName"));
    setEmail(localStorage.getItem("email"));

    const fetchTrainingsFromHistory = async () => {
        const response = await CustomAxios.get("http://localhost:8088/training/trainingHistory/user/" + email);

        console.log(response?.data);

        setTrainingHistory(response?.data);
    }

    fetchTrainingsFromHistory().catch(error => {
      console.log(error);
    })
  }, [email])

  return ( 
    <Container>
      <Row className="mt-5 mb-3">
        <h1>{user}'s profile</h1>
      </Row>
      <Row className="mb-3">
        <h2>Your finished trainings</h2>
      </Row>
      <div style={{display: "flex", flexDirection: "row"}}>
      {trainingHistory != null && trainingHistory.map((training) => {
      return <Card style={{ width: '18rem' }} className="mr-3 historyCard" key={training.id}>
        <Card.Img variant="top" src={training.coverImage} className="cardImgType"/>
        <Card.Body>
          <Card.Title>{training.name}</Card.Title>
          <Card.Text>
            Target area: {training.targetArea}
          </Card.Text>
          <Card.Text>
            Type: {training.type}
          </Card.Text>
        </Card.Body>
        <Card.Footer>
          <small>Duration: {training.duration} minutes</small>
        </Card.Footer>
      </Card>
      
      
      
      
      // <div className="card mb-3 cardHover" key={training.id}>
      //   <div className="row g-0">
      //     <div className="col-md-4">
      //       <img src={training.coverImage} className="img-fluid rounded-start cardImgType" alt={training.name}></img>
      //     </div>
      //     <div className="col-md-8">
      //         <div className="card-body">
      //           <h4 className="card-title">{training.name}</h4>
      //           <p className="card-text">{training.description}</p>
      //           <p className="card-text">Target area: {training.targetArea}</p>
      //           <p className="card-text">Duration: {training.duration} minutes.</p>
      //         </div>

      //     </div>
      //   </div>
      // </div>
      })}
      </div>
    </Container>
  )
}

export default Profile;