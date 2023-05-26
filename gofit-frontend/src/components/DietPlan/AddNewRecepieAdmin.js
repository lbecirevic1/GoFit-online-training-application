import React,{Component,useState} from 'react'
import '../../css/DietPlan/newRecepie.css'
import axios from 'axios'
import AuthService from "../../services/AuthService";
import { useNavigate, Link } from 'react-router-dom';
import CustomAxios from "../CustomAxios/CustomAxios";

const RecepieComponent = () => {
    const [subject,setSubject]=useState("");
    const[recepieText,setRecepieText]=useState("");
    const[dedicated,setDedicated]=useState("");
    let navigate = useNavigate();


    const recepieReq = {
        subject:subject,
        recepieText:recepieText,
        dedicated:dedicated
    };


    const handleRecepie = async()=>{
        await CustomAxios.post("http://localhost:8088/dietplan/addNewRecepie",{subject,recepieText,dedicated});
    }

    const handleSubmit=(event)=>{

    }

    const [formValue,setformValue]=React.useState({
        subject:'',
        recepie:'',
        dedicated:''
    })


    return(
        <div className='addRecepie'>
            <div className='container'>
                <div className='row'>
                    <h1 className='mb-5'>Add new Recepie <br/></h1>
                    <br/>

                </div>
                <div className='row'>
                    <div className='col'>

                        <div className='row form-row p-4'>
                            <input type="text" placeholder='Subject' className='form-control p-4' value={subject} onChange={(e) => setSubject(e.target.value)}/>
                        </div>
                        <div className='row form-row p-4'>
                            <input type="text" placeholder='Recepie Text' className='form-control p-4' value={recepieText} onChange={(e) => setRecepieText(e.target.value)}/>
                        </div>
                        <div className='row form-row p-4'>
                            <input type="text" placeholder='Dedicated' className='form-control p-4' value={dedicated} onChange={(e) => setDedicated(e.target.value)}/>
                        </div>


                    <div className='row form-row'>
                        <button type="submit" className='submitbtn mt-5 mb-5 ml-3 submitbtn' onClick={handleRecepie}>Submit</button>
                    </div>

                    </div>
                </div>
            </div>
        </div>
    )
}

export default RecepieComponent;