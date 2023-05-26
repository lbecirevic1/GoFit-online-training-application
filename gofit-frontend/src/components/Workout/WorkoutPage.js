import { useEffect, useState } from "react";
import { Container, Table, Button, Row, Col } from "react-bootstrap";
import ScheduleService from "../../services/ScheduleService";
import { format } from "date-fns";

const WorkoutPage = () => {
  const [workouts, setWorkouts] = useState([]);

  useEffect(() => {
    ScheduleService.getWorkouts().then((res) => {
      res.map((w) => {
        w.start = new Date(w.start);
        w.end = new Date(w.end);
        return w;
      });
      setWorkouts(res);
    });
  }, []);


  const addWorkout = () => {

  }

  return (
    <Container className="py-5">
      <Row className="pb-3">
        <Col sm={{span:9}} className="d-flex justify-content-between">
          <h1>Workout plan</h1>
        </Col>
        <Col sm={{span:3}}>
          <button className='btn btnExercise text-center' onClick={() => addWorkout(true)}>Add workout</button>
        </Col>
      </Row>
      <Row>
        <Col>
          <Table className="table-borderless table-hover">
            <thead className="p-5">
              <tr className="exerciseTableRowHead2 p-1 mb-3 d-flex align-items-center text-center primaryColorNoHover">
                <th className="col-lg-3">Title</th>
                <th className="col-lg-2">Start</th>
                <th className="col-lg-2">End</th>
                <th className="col-lg-3">Note</th>
              </tr>
            </thead>
            <tbody>
              {workouts !== null &&
                workouts.map((workout) => {
                  return (
                    <tr
                      key={workout.id}
                      className="exerciseTableRow2 d-flex align-items-center p-2 mb-3 primaryColor"
                    >
                      <td className="col-lg-3 text-center">{workout.title}</td>
                      <td className="col-lg-2 text-center">
                        {format(workout.start, "MMMM dd yyyy, h:mm:ss a")}
                      </td>
                      <td className="col-lg-2 text-center">
                        {format(workout.end, "MMMM dd yyyy, h:mm:ss a")}
                      </td>
                      <td className="col-lg-3 text-center">{workout.note}</td>
                    </tr>
                  );
                })}
            </tbody>
          </Table>
        </Col>
      </Row>
    </Container>
  );
};

export default WorkoutPage;
