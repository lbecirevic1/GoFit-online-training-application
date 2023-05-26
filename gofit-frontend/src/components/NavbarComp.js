import React, { Component, useState } from 'react'
import { Navbar, Nav, Container, NavItem, NavDropdown, Button } from 'react-bootstrap'
import "../css/navbar.css"
import {Link, useNavigate} from 'react-router-dom';
import jwt_decode from "jwt-decode";
import { useEffect } from 'react';

const logout = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem("roles");
    localStorage.clear();
};


const GuestNav = () => {
    return (
      <div>
        <Navbar expand="lg" className='navbar-class'>
          <Container>
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav.Link as={Link} to="/home" id='gofit-link'>GoFit</Nav.Link>
              <Nav className="me-auto">
                <Nav.Link as={Link} to="/login">Login</Nav.Link>
                <Nav.Link as={Link} to="/register">Register</Nav.Link>
              </Nav>
              <Nav className='d-flex ml-auto' >
                <Button className="secondaryColorNoHover registerButton"><Nav.Link as={Link} to="/register" className='registerNoHover'>Register</Nav.Link></Button>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>
    )
  }

  const LoggedUserNav = ({user}) => {
  return (

      <div>
        <Navbar expand="lg" className='navbar-class'>
          <Container>
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav.Link as={Link} to="/home" id='gofit-link'>GoFit</Nav.Link>
              <Nav>
                {/* <Nav.Link as={Link} to="/dashboard">Dashboard</Nav.Link> */}
                <Nav.Link as={Link} to="/training">Training</Nav.Link>
                <Nav.Link as={Link} to="/dietplan">Meals</Nav.Link>
                <Nav.Link as={Link} to="/schedule">Schedule</Nav.Link>
                {/* <Nav.Link as={Link} to="/" onClick={logout} className='ms-auto'>Logout</Nav.Link> */}
              </Nav>
              <NavDropdown
                id="nav-dropdown-dark-example"
                title={user}
                menuVariant="dark"
                className='d-flex ml-auto profileDropdown' 
              >
                <NavDropdown.Item as={Link} to="/profile">Profile</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item  as={Link} to="/" onClick={logout}>Logout</NavDropdown.Item>
              </NavDropdown>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>
    )};

  const LoggedAdminNav = () => {
  return (
      <div>
        <Navbar expand="lg" className='navbar-class'>
          <Container>
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav.Link as={Link} to="/home" id='gofit-link'>GoFit</Nav.Link>
              <Nav>
                <Nav.Link as={Link} to="/adminDashboard">Dashboard</Nav.Link>
                <Nav.Link as={Link} to="/adminTraining">Trainings</Nav.Link>
                <Nav.Link as={Link} to="/adminExercises">Exercises</Nav.Link>
                <Nav.Link as={Link} to="/users">Users</Nav.Link>
                <Nav.Link as={Link} to="/adminRecepies">Recipes</Nav.Link>
                <Nav.Link as={Link} to="/" onClick={logout} className='ms-auto'>Logout</Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>
    )};

const NavbarComp = () => {
  const navigate = useNavigate();

  if (localStorage.getItem("roles") === "ROLE_USER") {
    return <LoggedUserNav user={localStorage.getItem("usersName")} />;
  } else if (localStorage.getItem('roles') === "ROLE_ADMIN") {
    return <LoggedAdminNav />
  }
  return <GuestNav />;
}


export default NavbarComp;