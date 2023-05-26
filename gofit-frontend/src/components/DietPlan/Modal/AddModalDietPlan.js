import {  Button, Form, Col, Row, Dropdown} from "react-bootstrap";
import { useState } from "react";
// import { Modal } from "react-bootstrap";
import Modal from 'react-bootstrap/Modal'
import CustomAxios from "../../CustomAxios/CustomAxios";
import { type } from "@testing-library/user-event/dist/type";

const AddModalDietPlan = ({show, setShow, isRecipe}) => {


    const[dataRecipe,setDataRecipe]=useState({
        subject : "",
        dedicated : "",
        recepieText : ""
    })

    const createRecipe=async()=>{
        const response=await CustomAxios.post("http://localhost:8088/dietplan/addNewRecepie",dataRecipe)
        setShow(false);
    }

    return (
        <>
            {isRecipe && <Modal show={show} onHide={() => {setShow(false)}} animation={false}
                                  aria-labelledby="contained-modal-title-vcenter"
                                  centered
                                  dialogClassName="modal-60w">
                <Modal.Header  className="p-3">
                    <Modal.Title>Create new recipe</Modal.Title>
                </Modal.Header>
                <Modal.Body  className="p-3">
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Subject</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Breakfast/Lunch/Dinner"
                                autoFocus
                                value={dataRecipe.subject}
                                onChange={(e) => { setDataRecipe(prevState => { return {...prevState, subject: e.target.value} })}}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Dedicated (BMI)</Form.Label>
                            <Form.Control
                                type="number"
                                placeholder={1}
                                value={dataRecipe.dedicated}
                                onChange={(e) => { setDataRecipe(prevState => { return {...prevState, dedicated: e.target.value} })}}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                type="text"
                                value={dataRecipe.recepieText}
                                onChange={(e) => { setDataRecipe(prevState => { return {...prevState, recepieText: e.target.value} })}}
                            />
                        </Form.Group>


                    </Form>
                </Modal.Body>


                <Modal.Footer className="d-flex">
                    <Button variant="secondary" onClick={() => {setShow(false)}} className="defaultBtn mr-auto">
                        Close
                    </Button>
                    <Button variant="primary" className="defaultBtn" onClick={() => {
                        createRecipe();
                        setShow(false);
                        CustomAxios.get("http://localhost:8088/dietplan/recepies");
                    }}>
                        Create
                    </Button>
                </Modal.Footer>
            </Modal>}
        </>
    );
}

export default AddModalDietPlan;