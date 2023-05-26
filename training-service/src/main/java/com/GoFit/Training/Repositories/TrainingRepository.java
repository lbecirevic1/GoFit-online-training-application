package com.GoFit.Training.Repositories;

import com.GoFit.Training.Models.Exercise;
import com.GoFit.Training.Models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
    public Training findById(int id);
    public List<Training> findByType(String type);
    public List<Training> findByName(String type);
    public List<Training> findByDuration(Double duration);
    public List<Training> findByDurationAndType(Double duration, String type);
    public List<Training> findTrainingsByTargetArea(String targetArea);
    public List<Training> findByExercises(Exercise exercise);

    @Query("SELECT DISTINCT t.targetArea FROM Training t")
    List<String>[] findTargetArea();

    @Query("SELECT DISTINCT t.type FROM Training t")
    List<String>[] findType();
}
