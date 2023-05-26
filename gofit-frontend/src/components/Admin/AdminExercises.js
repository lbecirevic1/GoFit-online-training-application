import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Container, Row, Col, Button, Table } from "react-bootstrap";
import CustomAxios from "../CustomAxios/CustomAxios";
import AddModal from "../Training/Modal/AddModal";
import EditModal from "../Training/Modal/EditModal";

const AdminExercises = () => {
  const [exercises, setExercises] = useState(null);
  const [show, setShow] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [dataToEdit, setDataToEdit] = useState({});

  const [isTraining, setIsTraining] = useState(false);
  const [isExercise, setIsExercise] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      const response = await CustomAxios.get(
        "http://localhost:8088/training/exercises"
      );
      setExercises(response?.data);

      console.log("Fetched exercises");
      console.log(response?.data);
    };
    fetchData().catch((error) => {
      console.log(error);
    });
  }, [showEditModal, show]);

  const openEditModal = (exercise) => {
    console.log("exercise", exercise);
    setIsExercise(true);
    setShowEditModal(true);
    setDataToEdit(exercise);
  };

  const handleOnUpdate = async () => {
    setShowEditModal(false);
    const resp = await CustomAxios.put(
      "http://localhost:8088/training/exercise/put/" + dataToEdit.id, dataToEdit
    );
    if(resp.status === 201) {
      const response = await CustomAxios.get(
        "http://localhost:8088/training/exercises"
      );
      setExercises(response?.data);
    }
   
  };

  return (
    <Container className="bigContainer p-5 mb-5 secondaryColorNoHover">
      <Row className="ml-0 secondaryColorNoHover">
        <Col className="col-g-3">
          <h2 className="mb-5">Exercises</h2>
        </Col>
        <Col className="col-lg-2"></Col>
        <Col className="col-lg-2"></Col>
        <Col className="col-lg-2"></Col>
        <Col className="d-flex align-items-right secondaryColorNoHover">
          <Button
            className="btn btnExercise text-center"
            onClick={() => setShow(true)}
          >
            Add exercise
          </Button>
        </Col>
      </Row>
      <AddModal
        show={show}
        setShow={setShow}
        isTraining={isTraining}
        isExercise={isExercise}
      />
      <Table className="table-borderless table-hover">
        <thead className="p-5">
          <tr className="exerciseTableRowHead d-flex align-items-center p-2 mb-3 text-center primaryColorNoHover">
            <th className="col-lg-3">Image</th>
            <th className="col-lg-2">Name</th>
            <th className="col-lg-2">Duration</th>
            <th className="text-center col">Description</th>
          </tr>
        </thead>
        <tbody>
          {exercises !== null &&
            exercises.map((exercise) => {
              return (
                <tr
                  key={exercise.id}
                  className="exerciseTableRow d-flex align-items-center p-2 mb-3 primaryColor"
                  onClick={() => openEditModal(exercise)}>
                  <td className="col-lg-3 text-center">
                    <img
                      src={exercise.image}
                      alt="Exercise"
                      className="customImg"
                    ></img>
                  </td>
                  <td className="col-lg-2 text-center">{exercise.name}</td>
                  <td className="col-lg-2 text-center">{exercise.duration}</td>
                  <td>{exercise.description}</td>
                </tr>
              );
            })}
          <EditModal
            showEditModal={showEditModal}
            setShowEditModal={setShowEditModal}
            isExercise={isExercise}
            isTraining={isTraining}
            data={dataToEdit}
            setData={setDataToEdit}
            handleOnUpdate={handleOnUpdate}
          ></EditModal>
        </tbody>
      </Table>
    </Container>
  );
};

export default AdminExercises;
