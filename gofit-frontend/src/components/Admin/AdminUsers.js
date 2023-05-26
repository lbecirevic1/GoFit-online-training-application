import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Container, Row, Col, Button, Table} from 'react-bootstrap';
import CustomAxios from '../CustomAxios/CustomAxios';

const AdminUsers = () => {
  const [users, setUsers] = useState(null);

  useEffect(() => {
    const fetchData =  async () => {
       const response = await CustomAxios.get("http://localhost:8088/user/all");
       setUsers(response?.data);

       console.log("Fetched users");
       console.log(response?.data);
    }
    fetchData().catch(error => {
      console.log(error);
    })
  },[]);


  return (
    <Container className='bigContainer p-5 mb-5 secondaryColorNoHover'>
      <Row className='secondaryColorNoHover ml-0'>
        <Col className='secondaryColorNoHover col-g-3'>
          <h2 className='mb-5'>Users</h2>
        </Col>
        <Col className='col-lg-2'></Col>
        <Col className='col-lg-2'></Col>
        <Col className='col-lg-2'></Col>
        {/* <Col className='secondaryColorNoHover d-flex align-items-right'>
         <button className='btnGreen btnExercise text-center bgBlueUser'>Add training</button>
        </Col> */}
      </Row>
    <Table className='table-borderless table-hover'>
      <thead className='p-5'>
        <tr className="exerciseTableRowHead2 p-1 mb-3 d-flex align-items-center text-center primaryColorNoHover">
          <th className="col-lg-2">Name</th>
          <th className="col-lg-2">Last name</th>
          <th className="col-lg-2">Username</th>
          <th className="col-lg-2">Email</th>
          <th className="col-lg-2">Age</th>
          <th className="col-lg-2">Role</th>
        </tr>
      </thead>
      <tbody>
        {users !== null && users.map((user) => {
          return <tr key={user.id} className="exerciseTableRow2 d-flex align-items-center p-2 mb-3 primaryColor">
            <td className="col-lg-2 text-center">{user.name}</td>
            <td className="col-lg-2 text-center">{user.lastName}</td>
            <td className="col-lg-2 text-center">{user.username}</td>
            <td className="col-lg-2 text-center">{user.email}</td>
            <td className="col-lg-2 text-center">{user.age}</td>
            <td className="col-lg-2 text-center">{user.roleNames}</td>

          </tr>
        })}
      </tbody>
    </Table>
    </Container>
  )
}

export default AdminUsers;