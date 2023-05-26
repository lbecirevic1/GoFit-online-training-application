import React from "react";
import ScheduleService from "../../services/ScheduleService";
const { useState, useEffect } = require("react");
const { default: Workout } = require("./Workout");

const WorkoutList = ({filterList, selectedItems, setSelectedItems}) => {
  const [workouts, setWorkouts] = useState([]);

  useEffect(() => {
    ScheduleService.getTrainings().then(result=>{
      if(filterList?.length>0) {
        const filter = result.filter(tr=>{ 
         return  filterList.includes(tr.id)})
        setWorkouts(filter)
      }
      else setWorkouts(result)
    })
  }, []);

  const isSelected = (workoutId) => {
    return selectedItems.find((item) => filterList?.length>0  ? filterList.includes(workoutId) : item.id === workoutId) ;
  };

  return (
    <div>
      {workouts.map((workout, index) => {
        return (
          <Workout
            className={"pb-3"}
            onClick={setSelectedItems}
            key={`${index}__${workout.name}`}
            isSelected={isSelected}
            workout={workout}
          ></Workout>
        );
      })}
    </div>
  );
};

export default WorkoutList;
