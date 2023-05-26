import {  Button, Form, Col, Row, Dropdown} from "react-bootstrap";
import { useEffect, useState } from "react";
// import { Modal } from "react-bootstrap";
import Modal from 'react-bootstrap/Modal'
import CustomAxios from "../../CustomAxios/CustomAxios";

const EditModal = ({showEditModal, setShowEditModal, isRecipe, data,setData,handleOnUpdate }) => {

    const deleteRecipeHandler = async () => {
        console.log(data.id);
        const response = await CustomAxios.delete("http://localhost:8088/dietplan/deleteRecepie/"+data.id);
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
                    {isRecipe && <Modal.Title>Recipe</Modal.Title>}
                </Modal.Header>
                {isRecipe && <Modal.Body  className="p-3">
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Subject</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Breakfast/Lunch/Dinner"
                                autoFocus
                                value={data.subject}
                                onChange={(e) => { setData(prevState => { return {...prevState, subject: e.target.value} })}}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Dedicated</Form.Label>
                            <Form.Control
                                type="number"
                                placeholder="1"
                                value={data.dedicated}
                                onChange={(e) => { setData(prevState => { return {...prevState, dedicated: e.target.value} })}}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                type="text"
                                value={data.recepieText}
                                onChange={(e) => { setData(prevState => { return {...prevState, recepieText: e.target.value} })}}
                            />
                        </Form.Group>

                    </Form>
                </Modal.Body>}

                <Modal.Footer className="d-flex">
                    <Button variant="secondary" onClick={() => {setShowEditModal(false)}} className="defaultBtn mr-auto">
                        Close
                    </Button>
                    <Button variant="secondary" className="defaultBtn" onClick={deleteRecipeHandler}>
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