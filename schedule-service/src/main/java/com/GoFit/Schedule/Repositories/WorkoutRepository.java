package com.GoFit.Schedule.Repositories;

import com.GoFit.Schedule.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository <Workout, Integer>{
    public Workout findById(int id);
}
