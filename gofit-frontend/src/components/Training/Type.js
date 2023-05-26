import { useEffect, useState } from "react";
import { Container, Row, Col, Table} from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom"
import CustomAxios from "../CustomAxios/CustomAxios";

const Type = () => {
  const params = useParams(); //type of the training {}
  const navigate = useNavigate();

  const [trainings, setTrainings] = useState(null);
  const [query, setQuery] = useState('');
  const [searchParam] = useState(["duration", "name", "targetArea"]);

  useEffect(() => {
    const fetchTrainingByType = async () => {
      const response = await CustomAxios.get('http://localhost:8088/training/type/' + params.type);
      setTrainings(response?.data);
      console.log(response?.data);
    } 

    fetchTrainingByType().catch(error => {
      console.log(error);
    });
  }, []);

  const startTrainingHandler = (trainingId) => {
    navigate("/training/currentTraining/" + trainingId);
    console.log(trainingId);
  }

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

  return (
    <Container className="mt-5 pt-4 pb-5">
      <Row className="mb-4">
        <h1>Choose your {params.type} training</h1>
      </Row>
      <Row className="mb-3">
        <div className="col-md-4">
          <div className="input-group mb-3 search">
            <i className="fa fa-search"></i>
            <input type="text" className="form-control inputType form-controlType" placeholder="Search" value={query} onChange={(e) => setQuery(e.target.value)}></input>
          </div>
        </div>
      </Row>
    {trainings != null && search(trainings).map((training) => {
      return <div className="card mb-3 cardHover" key={training.id} onClick={() => startTrainingHandler(training.id)}>
        <div className="row g-0">
          <div className="col-md-4">
            <img src={training.coverImage} className="img-fluid rounded-start cardImgType" alt={training.name}></img>
          </div>
          <div className="col-md-8">
              <div className="card-body">
                <h4 className="card-title">{training.name}</h4>
                <p className="card-text">{training.description}</p>
                <p className="card-text">Target area: {training.targetArea}</p>
                <p className="card-text">Duration: {training.duration} minutes.</p>
              </div>

          </div>
        </div>
      </div>
    })}
    </Container>
  )


}
export default Type;