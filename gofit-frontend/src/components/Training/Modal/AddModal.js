import {  Button, Form, Col, Row, Dropdown} from "react-bootstrap";
import { useState } from "react";
// import { Modal } from "react-bootstrap";
import Modal from 'react-bootstrap/Modal'
import CustomAxios from "../../CustomAxios/CustomAxios";
import NextModal from "./NextModal";
import { type } from "@testing-library/user-event/dist/type";

const AddModal = ({show, setShow, isTraining, isExercise, exercises, setTraining}) => {

  const [next, setNext] = useState(false);
  const [ids, setIds] = useState([]);

  const [dataExercise, setDataExercise] = useState({
    name : "",
    duration : "",
    description : "",
    image : ""
  })

  const [dataTraining, setDataTraining] = useState({
    name : "",
    duration : "",
    type : "",
    targetArea : "",
    coverImage : "",
    videLink : "",
    description : "",
    exercises : [],
  })

  const createExercise = async () => {
    const response = await CustomAxios.post("http://localhost:8088/training/exercise", dataExercise);
    setShow(false);
    console.log(response?.data);
  }

  
    const createTraining = async () => {
      const response = await CustomAxios.post("http://localhost:8088/training/training", dataTraining);
      console.log(response?.data);
  }

  return (
    <>
      {isExercise && <Modal show={show} onHide={() => {setShow(false)}} animation={false}       
      aria-labelledby="contained-modal-title-vcenter"
      centered
      dialogClassName="modal-60w">
        <Modal.Header  className="p-3">
          <Modal.Title>Create new exercise</Modal.Title>
        </Modal.Header>
        <Modal.Body  className="p-3">
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Exercise name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Cruches"
                autoFocus
                value={dataExercise.name}
                onChange={(e) => { setDataExercise(prevState => { return {...prevState, name: e.target.value} })}}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Duration in seconds</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="30"
                  value={dataExercise.duration}
                  onChange={(e) => { setDataExercise(prevState => { return {...prevState, duration: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Image</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://images.pexels.com/photos/6454072/pexels-photo-6454072.jpeg?cs=srgb&dl=pexels-marta-wave-6454072.jpg&fm=jpg"
                  value={dataExercise.image}
                  onChange={(e) => { setDataExercise(prevState => { return {...prevState, image: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Description</Form.Label>
              <Form.Control as="textarea" rows={2}
                value={dataExercise.description}
                onChange={(e) => { setDataExercise(prevState => { return {...prevState, description: e.target.value} })}}
              />
            </Form.Group>
          </Form>
          </Modal.Body>

          
        <Modal.Footer className="d-flex">
          <Button variant="secondary" onClick={() => {setShow(false)}} className="defaultBtn mr-auto">
            Close
          </Button>
          <Button variant="primary" className="defaultBtn" onClick={() => {
              createExercise();
              setShow(false);
          }}>
            Create
          </Button>
        </Modal.Footer>
      </Modal>}

      {isTraining && <Modal show={show} onHide={() => {setShow(false);}} animation={false}       
      aria-labelledby="contained-modal-title-vcenter"
      centered
      dialogClassName="modal-60w">
        <Modal.Header  className="p-3">
          <Modal.Title>Create new training</Modal.Title>
          <Button variant="secondary" onClick={() => {setNext(true)}} className="defaultBtn">Next</Button>
        </Modal.Header>
        {isExercise && <Modal.Body  className="p-3">
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Exercise name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Cruches"
                autoFocus
                value={dataExercise.name}
                onChange={(e) => { setDataExercise(prevState => { return {...prevState, name: e.target.value} })}}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Duration in seconds</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="30"
                  value={dataExercise.duration}
                  onChange={(e) => { setDataExercise(prevState => { return {...prevState, duration: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Image</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://images.pexels.com/photos/6454072/pexels-photo-6454072.jpeg?cs=srgb&dl=pexels-marta-wave-6454072.jpg&fm=jpg"
                  value={dataExercise.image}
                  onChange={(e) => { setDataExercise(prevState => { return {...prevState, image: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Description</Form.Label>
              <Form.Control as="textarea" rows={2}
                value={dataExercise.description}
                onChange={(e) => { setDataExercise(prevState => { return {...prevState, description: e.target.value} })}}
              />
            </Form.Group>
          </Form>
          </Modal.Body>}

          {isTraining && <Modal.Body  className="p-3">
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Training name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Awesome abs cardio"
                autoFocus
                onChange={(e) => { setDataTraining(prevState => { return {...prevState, name: e.target.value} })}}
              />
          </Form.Group>
                      <Form.Group className="mb-3">
              <Row>
                <Col className="col-md-6 p-0 pr-1">
                  <Form.Label>Type</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Strength"
                      onChange={(e) => { setDataTraining(prevState => { return {...prevState, type: e.target.value} })}}
                    />
                </Col>
                <Col className="col-md-6 p-0">
                  <Form.Label>Target area</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Abs"
                      onChange={(e) => { setDataTraining(prevState => { return {...prevState, targetArea: e.target.value} })}}
                    />
                </Col>
              </Row>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Duration in minutes</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="30"
                  onChange={(e) => { setDataTraining(prevState => { return {...prevState, duration: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Link to video - embed</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://www.youtube.com/embed/zr08J6wB53Y"
                  onChange={(e) => { setDataTraining(prevState => { return {...prevState, videoLink: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Cover image</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://images.pexels.com/photos/6454072/pexels-photo-6454072.jpeg?cs=srgb&dl=pexels-marta-wave-6454072.jpg&fm=jpg"
                  onChange={(e) => { setDataTraining(prevState => { return {...prevState, coverImage: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Description</Form.Label>
              <Form.Control as="textarea" maxLength="100" rows={2} onChange={(e) => { setDataTraining(prevState => { return {...prevState, description: e.target.value} })}}/>
            </Form.Group>
          </Form>
          </Modal.Body>
          }
        <Modal.Footer className="d-flex">
          <Button variant="secondary" onClick={() => {setShow(false)}} className="defaultBtn mr-auto">
            Close
          </Button>
          <Button variant="primary" className="defaultBtn" onClick={() => {
              createTraining();
              setShow(false);
              // setTraining(prevState => { return {...prevState, name: dataTraining.name, duration: dataTraining.duration, type: dataTraining.type,
              //                         targetArea: dataTraining.targetArea, coverImage: dataTraining.coverImage
              //                     } });
          }}>
            Create
          </Button>
        </Modal.Footer>
        <NextModal next={next} setNext={setNext} exercises={exercises} ids={ids} setIds={setIds} dataTraining={dataTraining} setDataTraining={setDataTraining}></NextModal>
      </Modal>}
    </>
  );
}

export default AddModal;