import {  Button, Form, Col, Row, Dropdown} from "react-bootstrap";
import { useEffect, useState } from "react";
// import { Modal } from "react-bootstrap";
import Modal from 'react-bootstrap/Modal'
import CustomAxios from "../../CustomAxios/CustomAxios";

const EditModal = ({showEditModal, setShowEditModal, isExercise, isTraining, data, setData, handleOnUpdate }) => {

  const deleteExerciseHandler = async () => {
    console.log(data.id);
    const response = await CustomAxios.delete("http://localhost:8088/training/exercise/delete/" + data.id);
    console.log(response?.data);
    setShowEditModal(false);
  }

  return (
    <>
      <Modal show={showEditModal} onHide={() => {setShowEditModal(false)}} animation={false}       
      aria-labelledby="contained-modal-title-vcenter"
      centered
      dialogClassName="modal-60w">
        <Modal.Header  className="p-3">
          {isExercise && <Modal.Title>Exercise</Modal.Title>}
          {isTraining && <Modal.Title>Training</Modal.Title>}
        </Modal.Header>
        {isExercise && <Modal.Body  className="p-3">
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Exercise name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Cruches"
                autoFocus
                value={data.name}
                onChange={(e) => { setData(prevState => { return {...prevState, name: e.target.value} })}}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Duration in seconds</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="30"
                  value={data.duration}
                  onChange={(e) => { setData(prevState => { return {...prevState, duration: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Image</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://images.pexels.com/photos/6454072/pexels-photo-6454072.jpeg?cs=srgb&dl=pexels-marta-wave-6454072.jpg&fm=jpg"
                  value={data.image}
                  onChange={(e) => { setData(prevState => { return {...prevState, image: e.target.value} })}}
                />
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Description</Form.Label>
              <Form.Control as="textarea" rows={2}
                value={data.description}
                onChange={(e) => { setData(prevState => { return {...prevState, description: e.target.value} })}}
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
              />
          </Form.Group>
                      <Form.Group className="mb-3">
              <Row>
                <Col className="col-md-6 p-0 pr-1">
                  <Form.Label>Type</Form.Label>
                    <Form.Control
                      type="number"
                      placeholder="30"
                    />
                </Col>
                <Col className="col-md-6 p-0">
                  <Form.Label>Target area</Form.Label>
                    <Form.Control
                      type="number"
                      placeholder="30"
                    />
                </Col>
              </Row>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Duration in minutes</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="30"
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Link to video - embed</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://www.youtube.com/embed/zr08J6wB53Y"
                />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Cover image</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="https://images.pexels.com/photos/6454072/pexels-photo-6454072.jpeg?cs=srgb&dl=pexels-marta-wave-6454072.jpg&fm=jpg"
                />
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>Description</Form.Label>
              <Form.Control as="textarea" maxLength="10" rows={2} />
            </Form.Group>
          </Form>
          </Modal.Body>
          }
        <Modal.Footer className="d-flex">
          <Button variant="secondary" onClick={() => {setShowEditModal(false)}} className="defaultBtn mr-auto">
            Close
          </Button>
          <Button variant="secondary" className="defaultBtn" onClick={deleteExerciseHandler}>
            Delete
          </Button>
          <Button variant="primary" className="defaultBtn" onClick={handleOnUpdate}>
            Update
          </Button>

        </Modal.Footer>
      </Modal>
    </>
  );
}

export default EditModal;