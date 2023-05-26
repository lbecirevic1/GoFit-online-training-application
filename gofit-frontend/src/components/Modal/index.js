import React, { useEffect } from "react";
import {
  Button,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
} from "reactstrap";
import { FormGroup, Input, Form } from "reactstrap";
import { Alert } from "reactstrap";
import ScheduleService from "../../services/ScheduleService";
import WorkoutList from "../WorkoutList";
import styles from "./Modal.module.scss";

const _Modal = ({
  wsel,
editModal,
  data,
  className,
  isOpen,
  toggle,
  startDate,
  endDate,
  error,
  setError,
  updateData,
  createWorkout,
  selectedWorkouts,
  addToSelectedItems
}) => {
 

  useEffect(() => {
    setTimeout(() => {
      setError({ isError: false, message: "" });
    }, 5000);
  }, [error]);

  const deleteWorkout = () =>{
    ScheduleService.deleteWorkout(data.id).then((res)=>{
      toggle();
    })
   // ScheduleS
  }

  const editWorkout = () =>{
    console.log('data.id',data)
    ScheduleService.editWorkout(wsel.id,data).then((res)=>{
      toggle();
    })
   // ScheduleS
  }

  return (
    <div className={`${styles.modal} ${className}`}>
      <Modal isOpen={isOpen} toggle={toggle} className={className} scrollable>
        <ModalHeader>Create your workout</ModalHeader>
        <ModalBody className="p-4">
          {error.isError && <Alert color="danger">{error.message}</Alert>}
          <Form>
            <FormGroup className="pb-3">
              <h5>Title</h5>
              <Input
                type="text"
                defaultValue={data.title}
                placeholder="Workout title"
                onChange={(e) => {
                  updateData(e.target.value, "title");
                }}
              ></Input>
            </FormGroup>
            <h5 className="pb-3">Choose your exercises</h5>
            <WorkoutList
            filterList={data.trainings}
              selectedItems={selectedWorkouts}
              setSelectedItems={addToSelectedItems}
            />

            <FormGroup className="pt-3">
              <h5>Start time</h5>
              <Input
                type="datetime-local"
                onChange={(e) => {
                  updateData(e.target.value, "start");
                }}
                defaultValue={startDate.toISOString().substring(0, 19)}
              ></Input>
            </FormGroup>
            <FormGroup>
              <h5>End time</h5>
              <Input
                type="datetime-local"
                onChange={(e) => {
                  updateData(e.target.value, "end");
                }}
                defaultValue={endDate.toISOString().substring(0, 19)}
              ></Input>
            </FormGroup>

            <FormGroup className="pb-3">
              <h5>Note</h5>
              <Input
                type="textarea"
                defaultValue={data.note}
                placeholder="Type some notes"
                onChange={(e) => {
                  updateData(e.target.value, "note");
                }}
              ></Input>
            </FormGroup>
          </Form>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" className="w-100" onClick={editModal ? editWorkout : createWorkout}>
           {editModal ? 'Edit' : 'Create' }
          </Button>
         {editModal && <Button color="primary" className="w-100" onClick={()=> deleteWorkout()}>
           Delete 
          </Button>}
          <Button color="secondary" className="w-100" onClick={toggle}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );
};

export default _Modal;
