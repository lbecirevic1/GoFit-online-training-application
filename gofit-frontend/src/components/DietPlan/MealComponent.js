import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Container, Row, Col, Button, Table} from 'react-bootstrap';
import CustomAxios from '../CustomAxios/CustomAxios';
import axios from "axios";
import AddModalTest from "./Modal/AddModalTest";
import jwt_decode from "jwt-decode";


const TestDone=()=>{
    const [recepie, setRecepie] = useState(null);
    const[id,setId]=useState(null);
    const[subject,setSubject]=useState(null);
    const[tekst,setTekst]=useState(null);
    const[recepieText,setRecepieText]=useState(null);
    const [isRecipe, setIsRecipe] = useState(true);
    const[isTest,setIsTest]=useState(true);
    const [show, setShow] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);
    const [dataToEdit, setDataToEdit] = useState({});
    let navigate=useNavigate();
    var id2=jwt_decode(localStorage.getItem("accessToken"))
    useEffect(() => {
        const fetchData =  async () => {
            const response = await CustomAxios.get("http://localhost:8088/dietplan/getRecepiesForUser/"+id2.sub);
            setRecepie(response?.data);

            console.log("Fetched recepies");
            console.log(response?.data);
        }
        fetchData().catch(error => {
            console.log(error);
        })

    },[]);

    return (
        <Container className='bigContainer p-5 mb-5'>

            <Row className="mb-5">
                <Col className="col-6">
                    <h1>Recipes</h1>
                </Col>
                <Col className="col-6 d-flex">
                    <button className='btn btnExercise text-center ml-auto' style={{width:"50%"}} onClick={() => setShow(true)}>Take a test</button>

                </Col>
            </Row>

            <AddModalTest show={show} setShow={setShow} isTest={isTest}/>
            {recepie != null && recepie.map((recepie) => {
                return <div className="card mb-5" key={recepie.id}>
                    <div className="row g-0">
                        <div className="col-md-4">
                            <img src={recepie.imagePath} className="img-fluid rounded-start cardImgType" alt={recepie.subject}></img>
                        </div>
                        <div className="col-md-8">
                            <div className="card-body">
                                <h4 className="card-title">{recepie.description}</h4>
                                <p className="card-text">{recepie.recepies}</p>
                            </div>

                        </div>
                    </div>
                </div>
            })}
        </Container>
    )
}

const NoTest=()=>{
    const[isTest,setIsTest]=useState(true);
    const [show, setShow] = useState(false);
    return(

        <div className='dietplan'>

            <div className='container'>
                <div className='text p-4'>
                    <h3>Your personalized meal plan is set up and ready to enhance your <br/>
                        fitness program so you can reach your goal even faster.
                    </h3>
                </div>
                <div className='buttondiv'>
                    <button type="button" className='testbtn mt-5 mb-5 ml-3 testbtn' onClick={() => setShow(true)}>Take a test</button>
                    <AddModalTest show={show} setShow={setShow} isTest={isTest}/>
                </div>
            </div>
        </div>

    ) }


const MealComponent = () => {
    const [recepie, setRecepie] = useState(null);
    const[id,setId]=useState(null);
    const[subject,setSubject]=useState(null);
    const[tekst,setTekst]=useState(null);
    const[recepieText,setRecepieText]=useState(null);
    const [isRecipe, setIsRecipe] = useState(true);
    const[isTest,setIsTest]=useState(true);
    const [show, setShow] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);
    const [dataToEdit, setDataToEdit] = useState({});
    const[pomocna,setPomocna]=useState(null);
    let navigate=useNavigate();
    var id2=jwt_decode(localStorage.getItem("accessToken"))

    useEffect(() => {
        const fetchData =  async () => {

            const resp2=await CustomAxios.get("http://localhost:8088/dietplan/userHasPC/" + id2.sub);
            setPomocna(resp2?.data);
            console.log(resp2?.data);



        }
        fetchData().catch(error => {
            console.log(error);
        })

    },[]);

   if(pomocna==="not found"){
       return <NoTest />
   }
   else return <TestDone />


}

export default MealComponent;