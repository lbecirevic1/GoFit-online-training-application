import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Container, Row, Col, Button, Table} from 'react-bootstrap';
import CustomAxios from '../CustomAxios/CustomAxios';
import AddModal from '../Training/Modal/AddModal';

const AdminTrainings = () => {
  const [training, setTraining] = useState(null);
  const [exercises, setExercises] = useState(null);

  const [isTraining, setIsTraining] = useState(true);
  const [isExercise, setIsExercise] = useState(false);
  const [show, setShow] = useState(false);

  useEffect(() => {
    const fetchData =  async () => {
       const response = await CustomAxios.get("http://localhost:8088/training/trainings");
       setTraining(response?.data);

       console.log("Fetched training");
       console.log(response?.data);
    }

    const fetchExercises = async () => {
      const response = await CustomAxios.get("http://localhost:8088/training/exercises");
       setExercises(response?.data);
       console.log("Fetched exercises");
       console.log(response?.data);
    }
  
    fetchExercises().catch(error => {
      console.log(error);
    })
    fetchData().catch(error => {
      console.log(error);
    })
  }, []);


  return (
    <Container className='bigContainer p-5 mb-5 secondaryColorNoHover'>
      <Row className='secondaryColorNoHover ml-0'>
        <Col className='secondaryColorNoHover col-g-3'>
          <h2 className='mb-5'>Training</h2>
        </Col>
        <Col className='col-lg-2'></Col>
        <Col className='col-lg-2'></Col>
        <Col className='col-lg-2'></Col>
        <Col className='secondaryColorNoHover d-flex align-items-right'>
         <button className='btn btnExercise text-center' onClick={() => setShow(true)}>Add training</button>
        </Col>
      </Row>
      <AddModal show={show} setShow={setShow} isExercise={isExercise} isTraining={isTraining} exercises={exercises} setTraining={setTraining}/>
    <Table className='table-borderless table-hover'>
      <thead className='p-5'>
        <tr className="exerciseTableRowHead2 p-1 mb-3 d-flex align-items-center text-center primaryColorNoHover">
          <th className="col-lg-3">Cover image</th>
          <th className="col-lg-2">Name</th>
          <th className="col-lg-2">Target area</th>
          <th className="col-lg-2">Duration</th>
          <th className="col-lg-2">Type</th>

        </tr>
      </thead>
      <tbody>
        {training !== null && training.map((training) => {
          return <tr key={training.id} className="exerciseTableRow2 d-flex align-items-center p-2 mb-3 primaryColor">
            <td className="col-lg-3 text-center"><img src={training.coverImage} alt="training" className='customImg'></img></td>
            <td className="col-lg-2 text-center">{training.name}</td>
            <td className="col-lg-2 text-center">{training.targetArea}</td>
            <td className="col-lg-2 text-center">{training.duration}</td>
            <td className="col-lg-2 text-center">{training.type}</td>
          </tr>
        })}
      </tbody>
    </Table>
    </Container>
  )
}

export default AdminTrainings;