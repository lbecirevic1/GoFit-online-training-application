package com.GoFit.Training.Repositories;

import com.GoFit.Training.Models.Exercise;
import com.GoFit.Training.Models.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    public Exercise findById(int id);
    public Set<Exercise> findByIdIn(List<Integer> exerciseIdList);

    public List<Exercise> findByTrainings(Training training);

//    public List<Exercise> findExercisesByTrainings(List<Training> trainings);
}
