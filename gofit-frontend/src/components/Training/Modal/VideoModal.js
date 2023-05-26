import { useState } from "react";
import "./Modal.css";

const VideoModal = ({videoSource, setOpenModal, setVote}) => {

  const [finishedTraining, setFinishedTraining] = useState('');

  return (
    <div className="showVideo">
      <div className="modalContainer">
        <div className="body">
         <iframe className="videoBorder" src={videoSource} title="YouTube video player" frameBorder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowFullScreen></iframe>
        </div>
        <div className="d-flex  mt-3">
        <div className="footer mx-auto">
          <button onClick={() => {setOpenModal(false); setVote(false)}} className="btn modalButtonFinish cancelBtn mr-2"> Cancel </button>
          <button className="btn modalButtonFinish cancelBtn" onClick={() => {setOpenModal(false); setVote(true); console.log("clicked") }}>Finish</button>
        </div>
        </div>

      </div>
    </div>
  
  )
}

export default VideoModal;