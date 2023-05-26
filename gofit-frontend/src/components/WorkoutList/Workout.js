import React from "react";
import { Media } from "reactstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleCheck } from "@fortawesome/free-solid-svg-icons";

import styles from "./Workout.module.scss";

const Workout = ({ workout, className, isSelected, onClick }) => {
  return (
      <div className={`${className}`} onClick={()=>onClick(workout)}>
        
        <Media>
          <Media left href="#">
            <Media
              className={styles.workoutImage}
              src={workout.coverImage}
              object
              alt={workout.name}
            />
          </Media>
          <Media body className="pl-3">
            <Media heading>{workout.name} {isSelected(workout.id) ? <FontAwesomeIcon className={styles.selectedItem} icon={faCircleCheck} /> : ''}</Media>
            {workout.description}
            
          </Media>
        </Media>
      </div>
  );
};

export default Workout;
