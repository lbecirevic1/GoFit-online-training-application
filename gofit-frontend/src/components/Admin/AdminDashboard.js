import { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { Row, Col, Container } from 'react-bootstrap';
import CustomAxios from '../CustomAxios/CustomAxios';
const AdminDashboard = () => {
  const [noExercises, setNoExercises] = useState('');
  const [noTraining, setNoTraining] = useState('');
  const [noUsers, setNoUsers] = useState('');
  const [noRecipes, setNoRecipes] = useState('');

  useEffect(() => {
    const getNoOfExercises = async () => {
    const response = await CustomAxios.get("http://localhost:8088/training/exercise/count");
    setNoExercises(response?.data);

    console.log(response?.data);
    }

    const getNoTraining = async () => {
    const response = await CustomAxios.get("http://localhost:8088/training/training/count");
    setNoTraining(response?.data);

    console.log(response?.data);
    }

    const getNoUsers = async () => {
    const response = await CustomAxios.get("http://localhost:8088/user/count");
    setNoUsers(response?.data);

    console.log(response?.data);
    }


    const getNoRecipes = async () => {
      const response = await CustomAxios.get("http://localhost:8088/dietplan/count");
      setNoRecipes(response?.data);

      console.log(response?.data);
    }


    getNoOfExercises().catch(error => {
      console.log(error);
    });

    
    getNoTraining().catch(error => {
      console.log(error);
    });

    
    getNoUsers().catch(error => {
      console.log(error);
    });

    getNoRecipes().catch(error=>{
      console.log(error);
    })
  }, []);


  return (
    <div>
      <Container className='cardContainer align-items-center'>
        <Row>
          <h3 className='mt-5 mb-5 ml-0 mr-0'>Manage data</h3>
        </Row>
        <Row className='align-items-center'>
          <Col as={Link} to='/adminTraining' className='cardAsLink'>
            <div className="card customCard secondaryColor">
              <div className="card-body">
                <h5 className="card-title">Training</h5>
                <h6 className="card-subtitle mb-2 text-muted">Manage</h6>
                <p className="card-text mt-1">Edit or delete training from its list or add new training.</p>
                <p className='mt-5'>Total number of training <b className=''>{noTraining}</b>.</p>
              </div>
            </div>
          </Col>
          <Col as={Link} to='/adminExercises' className='cardAsLink'>
            <div className="card customCard secondaryColor">
              <div className="card-body">
                <h5 className="card-title">Exercises</h5>
                <h6 className="card-subtitle mb-2 text-muted">Manage</h6>
                <p className="card-text mt-1">Edit or delete exercise from its list or add new exercise.</p>
                <p className='mt-5'>Total number of exercises <b className=''>{noExercises}</b>.</p>
              </div>
            </div>
          </Col>
          <Col as={Link} to='/users' className='cardAsLink'>
            <div className="card customCard secondaryColor">
              <div className="card-body">
                <h5 className="card-title">Users</h5>
                <h6 className="card-subtitle mb-2 text-muted">Manage</h6>
                <p className="card-text mt-1">Edit or delete user from its list or add new user.</p>
                <p className='mt-5'>Total number of users <b className=''>{noUsers}</b>.</p>
              </div>
            </div>
          </Col>
          <Col as={Link} to='/adminRecepies' className='cardAsLink'>
            <div className="card customCard secondaryColor">
              <div className="card-body">
                <h5 className="card-title">Diet plan recipes</h5>
                <h6 className="card-subtitle mb-2 text-muted">Manage</h6>
                <p className="card-text mt-1">Edit or delete recipes from its list or add new recipes.</p>
                <p className='mt-5'>Total number of recipes <b className=''>{noRecipes}</b>.</p>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  )
}

export default AdminDashboard;