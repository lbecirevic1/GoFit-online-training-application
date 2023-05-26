import {  Button, Form, Col, Row, Dropdown} from "react-bootstrap";
import { useState } from "react";
// import { Modal } from "react-bootstrap";
import Modal from 'react-bootstrap/Modal'
import CustomAxios from "../../CustomAxios/CustomAxios";
import { type } from "@testing-library/user-event/dist/type";
import jwt_decode from "jwt-decode";

const AddModalTest = ({show, setShow, isTest}) => {

    var id=jwt_decode(localStorage.getItem("accessToken"))
    console.log(id.id)
    const[dataTest,setDataTest]=useState({
        mail : id.sub,
        gender : "",
        bodyType : "",
        height : "",
        weight : "",
        physicalActivity : ""
    })

    const createTest=async()=>{
        const response=await CustomAxios.post("http://localhost:8088/dietplan/physicalCondition",dataTest)
        setShow(false);
    }

    return (
        <>
            {isTest && <Modal show={show} onHide={() => {setShow(false)}} animation={false}
                                aria-labelledby="contained-modal-title-vcenter"
                                centered
                                dialogClassName="modal-60w">
                <Modal.Header  className="p-3">
                    <Modal.Title>Take new test</Modal.Title>
                </Modal.Header>
                <Modal.Body  className="p-3">
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Gender</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Type M for male, F for female"
                                autoFocus
                                value={dataTest.gender}
                                onChange={(e) => { setDataTest(prevState => { return {...prevState, gender: e.target.value} })}}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>BodyType</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Type normal/fat/skinny"
                                value={dataTest.bodyType}
                                onChange={(e) => { setDataTest(prevState => { return {...prevState, bodyType: e.target.value} })}}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Height</Form.Label>
                            <Form.Control
                                type="number"
                                value={dataTest.height}
                                onChange={(e) => { setDataTest(prevState => { return {...prevState, height: e.target.value} })}}
                            />
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Weight</Form.Label>
                            <Form.Control
                                type="number"
                                value={dataTest.weight}
                                onChange={(e) => { setDataTest(prevState => { return {...prevState, weight: e.target.value} })}}
                            />
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>PhysicalActivity</Form.Label>
                            <Form.Control
                                type="number"
                                placeholder="Enter 1 if you train, 5 if you never do sports"
                                value={dataTest.physicalActivity}
                                onChange={(e) => { setDataTest(prevState => { return {...prevState, physicalActivity: e.target.value} })}}
                            />
                        </Form.Group>


                    </Form>
                </Modal.Body>


                <Modal.Footer className="d-flex">
                    <Button variant="secondary" onClick={() => {setShow(false)}} className="defaultBtn mr-auto">
                        Close
                    </Button>
                    <Button variant="primary" className="defaultBtn" onClick={() => {
                        createTest();
                        setShow(false);
                    }}>
                        Take the test!
                    </Button>
                </Modal.Footer>
            </Modal>}
        </>
    );
}

export default AddModalTest;