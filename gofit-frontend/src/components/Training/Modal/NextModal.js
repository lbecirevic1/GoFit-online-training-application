import {  Button, Form, Col, Row, Dropdown, Card} from "react-bootstrap";
import Modal from 'react-bootstrap/Modal';

import { useState } from "react";

const NextModal = ({ next, setNext, exercises, ids, setIds, dataTraining, setDataTraining}) => {
  const [searchParam] = useState(["name"]);
  const [query, setQuery] = useState('');

  const [exerciseIds, setExerciseIds] = useState([]);

  function search(items) {
    return items.filter((item) => {
        return searchParam.some((newItem) => {
            return (
                item[newItem]
                    .toString()
                    .toLowerCase()
                    .indexOf(query.toLowerCase()) > -1
            );
        });
    });
  }

  const handleChange = (event) => {
    if (event.target.checked) {
      setExerciseIds((oldArray) => [...oldArray, event.target.value])
      setDataTraining(prevState => { return {...prevState, exercises: exerciseIds} });
    } else {
      setExerciseIds((oldArray) => {return oldArray?.filter(exerciseId => exerciseId !== event.target.value)})
      setDataTraining(prevState => {return {...prevState, exercises: exerciseIds}});
    }
  }
  
  return (
    <>
      <Modal show={next} onHide={() => {setNext(false)}} animation={false}     
        centered
        size="lg"
        aria-labelledby="contained-modal-title-vcenter" className="modal-dialog modal-dialog-scrollable">
        <Modal.Header  className="p-3">
        <Modal.Title id="contained-modal-title-vcenter">Pick exercises </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row className="mb-3">
            <div className="col-md-8">
              <div className="input-group mb-3 search">
                <i className="fa fa-search"></i>
                <input type="text" className="form-control inputType form-controlType" placeholder="Search exercises" value={query} onChange={(e) => setQuery(e.target.value)}></input>
              </div>
            </div>
          </Row>
          {exercises !== null && search(exercises).map((exercise) => {
            return <div className="card mb-3 historyCard nextCard" key={exercise.id}>
              <div className="card-header">
                <input type="checkbox" value={exercise.id} onChange={handleChange}/>
              </div>
              <div className="card-body">
                <h5 className="card-title">{exercise.name}</h5>
                <p className="card-text nextCardText">{exercise.description}</p>
                <p className="card-text nextCardText"><small className="text-muted">Duration: {exercise.duration} seconds</small></p>
              </div>
            </div>
          })}

        </Modal.Body>
      <Modal.Footer className="d-flex">
        <Button variant="secondary" onClick={() => {setNext(false)}} className="defaultBtn mr-auto">
          Back
        </Button>
        <Button variant="primary" className="defaultBtn" onClick={() => {setNext(false); console.log(dataTraining.exercises); console.log(exerciseIds); setIds(exerciseIds);  setExerciseIds([]);}}>
          OK
        </Button>
      </Modal.Footer>
    </Modal>
    </>
  )
}

export default NextModal;