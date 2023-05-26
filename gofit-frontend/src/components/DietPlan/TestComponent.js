import React,{Component,useState} from 'react'
import '../../css/DietPlan/test.css'
import axios from 'axios'
import AuthService from "../../services/AuthService";
import { useNavigate, Link } from 'react-router-dom';

const TestComponent = () => {
    const [gender, setGender] = useState("");
    const [bodyType, setBodyType] = useState("");
    const [height, setHeight] = useState("");
    const [weight, setWeight] = useState("");
    const [bmi, setBmi] = useState("");
    const [physicalActivity, setPhysicalActivity] = useState("");
    const [success, setSuccess] = useState("");
    let navigate = useNavigate();

    const token = JSON.parse(localStorage.getItem("token"))
    const headers = {
        'Authorization': 'Bearer token',
        'Token': token
    }
    const phycondition = {
        gender: gender,
        bodyType: bodyType,
        height: height,
        weight: weight,
        bmi: bmi,
        physicalActivity: physicalActivity
    };
    const handle=axios.post('http://localhost:8088/dietplan/physicalCondition', phycondition, {headers})
        .then(response => this.setState({phyconditionId: response.data.id}));

    function handleTest(){
        return handle;
}

return(
    <div className='dietplantesting'>
        <div className='container'>
            <div className='row'>
                <h1 className='mb-5'>Physical activity testing <br/></h1>
                <br/>

            </div>
            <div className='row'>
                <div className='col'>
                    <div className='row form-row p-4'>
                        <input type="text" placeholder='Gender' className='form-control p-4' value={gender} onChange={(e) => setGender(e.target.value)}/>
                    </div>
                    <div className='row form-row p-4'>
                        <input type="text" placeholder='BodyType' className='form-control p-4' value={bodyType} onChange={(e) => setBodyType(e.target.value)}/>
                    </div>
                    <div className='row form-row p-4'>
                        <input type="text" placeholder='Height' className='form-control p-4' value={height} onChange={(e) => setHeight(e.target.value)}/>
                    </div>
                    <div className='row form-row p-4'>
                        <input type="text" placeholder='Weight' className='form-control p-4' value={weight} onChange={(e) => setWeight(e.target.value)}/>
                    </div>
                    <div className='row form-row p-4'>
                        <input type="text" placeholder='Physical Activity' className='form-control p-4' value={physicalActivity} onChange={(e) => setPhysicalActivity(e.target.value)}/>
                    </div>
                </div>
                <div className='row form-row'>
                    <button type="button" className='submitbtn mt-5 mb-5 ml-3 submitbtn' onClick={handleTest}>Submit</button>
                </div>
            </div>
            </div>
        </div>
    )
}

            export default TestComponent;