import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Container, Col, Row, Table, Button } from "react-bootstrap";

import './Training.css';
import CustomAxios from "../CustomAxios/CustomAxios";
import TargetAreaMain from "./TargetAreaMain";
import TypeMain from "./TypeMain";

const TrainingMain = () => {

  return (
    <div className="mb-5">
      <TypeMain></TypeMain>
      <TargetAreaMain></TargetAreaMain>
    </div>
  )
}

export default TrainingMain;