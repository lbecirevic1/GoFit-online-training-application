import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Container, Row, Col, Button, Table} from 'react-bootstrap';
import CustomAxios from '../CustomAxios/CustomAxios';
import axios from "axios";
import AddModalDietPlan from "../DietPlan/Modal/AddModalDietPlan"
import EditModal from "../DietPlan/Modal/EditModalDietPlan";

const AdminRecepies = () => {
  const [recepie, setRecepie] = useState(null);
  const[id,setId]=useState(null);
  const[subject,setSubject]=useState(null);
  const[tekst,setTekst]=useState(null);
  const[recepieText,setRecepieText]=useState(null);
  const [isRecipe, setIsRecipe] = useState(true);
  const [show, setShow] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [dataToEdit, setDataToEdit] = useState({});
  let navigate=useNavigate();
  useEffect(() => {
    const fetchData =  async () => {
      const response = await CustomAxios.get("http://localhost:8088/dietplan/recepies");
      setRecepie(response?.data);

      console.log("Fetched recepies");
      console.log(response?.data);
    }
    fetchData().catch(error => {
      console.log(error);
    })

  },[showEditModal,show]);


  const newRecepieHandler = () => {
    navigate('/newRecepie');
  }

  const editRecepieHandler = () => {
    navigate('/editRecepie');
  }

  const token = JSON.parse(localStorage.getItem("token"))
  const headers = {
    'Authorization': 'Bearer token',
    'Token': token
  }
  const deleteRecepie=async (id)=>{
    await CustomAxios.delete(`http://localhost:8088/dietplan/deleteRecepie/${id}`);
  }
  const openEditModal = (receipe) => {
    console.log("recipe",recepie);
    setIsRecipe(true);
    setShowEditModal(true);
    setDataToEdit(receipe);
  };

  const handleOnUpdate = async () => {
    setShowEditModal(false);
    const resp = await CustomAxios.put(
        "http://localhost:8088/dietplan/editRecepie/" + dataToEdit.id, dataToEdit
    );
    if(resp.status === 200) {
      const response = await CustomAxios.get(
          "http://localhost:8088/dietplan/recepies"
      );
      setRecepie(response?.data);
    }

  };



  return (
      <Container className='bigContainer p-5 mb-5 secondaryColorNoHover'>
        <Row className=' ml-0 secondaryColorNoHover'>
          <Col className=' col-g-3 secondaryColorNoHover'>
            <h2 className='mb-5'>Recipes</h2>
          </Col>
          <Col className='col-lg-2'></Col>
          <Col className='col-lg-2'></Col>
          <Col className='col-lg-2 secondaryColorNoHover'></Col>
          <Col className=' d-flex align-items-right'>
            <button className='btn btnExercise text-center' onClick={() => setShow(true)}>Add recipe</button>
          </Col>
        </Row>
        <AddModalDietPlan show={show} setShow={setShow} isRecipe={isRecipe}/>
        <Table className='table-borderless table-hover'>
          <thead className='p-5'>
          <tr className="exerciseTableRowHead2 p-1 mb-3 d-flex align-items-center text-center primaryColorNoHover ">
            <th className="col-lg-3">Subject</th>
            <th className="col-lg-3">Description</th>
            <th className="col-lg-3">Dedicated to (BMI group)</th>
            <th className="col-lg-3">Image</th>
          </tr>
          </thead>
          <tbody>
          {recepie !== null && recepie.map((recepie) => {
            var id=recepie.id;
            console.log(id.toString());
            return(
              <tr
                  key={recepie.id}
                  className="exerciseTableRow d-flex align-items-center p-2 mb-3 primaryColor"
                  onClick={() => openEditModal(recepie)}
              >
              <td className="col-lg-3 text-center">{recepie.subject}</td>
              <td className="col-lg-3 text-center">{recepie.recepieText}</td>

              <td className="col-lg-3 text-center">{recepie.dedicated}</td>
              <td className="col-lg-3 text-center"><img src={recepie.imagePath} alt="Recepie" className='customImg'></img></td>
              </tr>
            );
          })}
                <EditModal
                    showEditModal={showEditModal}
                    setShowEditModal={setShowEditModal}
                    isRecipe={isRecipe}
                    data={dataToEdit}
                    setData={setDataToEdit}
                    handleOnUpdate={handleOnUpdate}
                ></EditModal>
          </tbody>
        </Table>
      </Container>
  )
}

export default AdminRecepies;