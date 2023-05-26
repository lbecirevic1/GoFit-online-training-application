import React, { Component }  from 'react';
import { createRoot } from 'react-dom/client';import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from 'react-router-dom';
//import 'react-big-calendar/lib/addons/dragAndDrop/styles';
//import "react-big-calendar/lib/addons/dragAndDrop/styles.css";
import "react-big-calendar/lib/css/react-big-calendar.css"; 
import axios from 'axios';


const container = document.getElementById('root');
const root = createRoot(container);
root.render(
      <BrowserRouter>
            <App />
      </BrowserRouter>
);

