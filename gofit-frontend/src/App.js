import React, { Component }  from 'react';
import './App.css';
import NavbarComp from './components/NavbarComp';
import { useNavigate, Link } from 'react-router-dom';
import {
  BrowserRouter,
  Route,
    Router,
  Routes
} from "react-router-dom";
import FooterComp from './components/FooterComp';
import About from './pages/About';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';
import ResetLink from './components/Password/ResetLinkComp'
import Training from './pages/Training'
import AdminPage from './pages/AdminPage'
import TestingPage from './pages/DietPlan/Testing'
import DietPlan from './pages/DietPlan/DietPlan'
import Dashboard from "./pages/Dashboard"
import Schedule from "./pages/Schedule";
import CalendarPage from './pages/CalendarPage';
import NewRecepie from './pages/DietPlan/NewRecepie';
import Recepies from './pages/DietPlan/Recepies'


import AdminDashboard from './components/Admin/AdminDashboard';
import AdminExercises from './components/Admin/AdminExercises';
import AdminTrainings from './components/Admin/AdminTrainings';
import ChangePassword from './components/Password/ChangePassword';
import AdminUsers from './components/Admin/AdminUsers';
import AdminRecepies from './components/Admin/AdminRecepies';

import TrainingMain from './components/Training/TrainingMain';
import Type from './components/Training/Type';
import TargetArea from './components/Training/TargetArea';
import CurrentTraining from './components/Training/CurrentTraining';
import AddModal from './components/Training/Modal/AddModal';
import Profile from './components/Profile';
import WorkoutPage from './components/Workout/WorkoutPage';

function App() {
  return (
    <>
      <NavbarComp/>
      <Routes>
        <Route path="/">
          <Route path="" element={<Home />} />
          <Route path="home" element={<Home />} />
          <Route path="about" element={<About />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path='/resetLink' element={<ResetLink />} />
          {/* <Route path='/training' element={<Training />} /> */}
          <Route path='/admin' element={<AdminPage />} />
          <Route path='/test' element={<TestingPage />} />
          <Route path='/dietplan' element={<DietPlan />} />
          {/* <Route path='/dashboard' element={<Dashboard />} /> */}
          <Route path='/schedule' element={<Schedule />} />
          <Route path='/calendar' element={<CalendarPage />} />
          <Route path='/myworkouts' element={<WorkoutPage />} />
          <Route path='newRecepie' element={<NewRecepie />} />
          <Route path='/recepies' element={<Recepies />} />


          <Route path='/changePassword' element={<ChangePassword />} />
          <Route path='/adminDashboard' element={<AdminDashboard />} />
          <Route path='/adminExercises' element={<AdminExercises />} />
          <Route path='/adminTraining' element={<AdminTrainings />} />
          <Route path='/users' element={<AdminUsers />} />
          <Route path='/adminRecepies' element={<AdminRecepies />} />

          <Route path='/training' element={<TrainingMain />}></Route>
          <Route path='/training/type/:type' element={<Type />} />
          <Route path='/training/targetArea/:targetArea' element={<TargetArea />} />
          <Route path='/training/currentTraining/:id' element={<CurrentTraining />} />

          <Route path='/training/modalshow' element={<AddModal />} />

          <Route path='/profile' element={<Profile />} />

        </Route>
      </Routes>
      </>
  );
}

export default App;
