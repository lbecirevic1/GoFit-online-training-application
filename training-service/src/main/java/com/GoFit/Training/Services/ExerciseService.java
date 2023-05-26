package com.GoFit.Training.Services;

import com.GoFit.Training.Exceptions.NotFoundException;
import com.GoFit.Training.Models.Exercise;
import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Models.TrainingHistory;
import com.GoFit.Training.Repositories.ExerciseRepository;
import com.GoFit.Training.Repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    public Exercise saveExercise (Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> saveExercises(List<Exercise> exercises) {
        return exerciseRepository.saveAll(exercises);
    }

    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(int id) {
        Exercise exercise =  exerciseRepository.findById(id);
        if(exercise == null) {
            throw new NotFoundException("Exercise with id = " + id + " does not exist.");
        }
        return exercise;
    }


    public Exercise putExercise(int id, Exercise exercise) {
        Exercise existingExercise = exerciseRepository.findById(id);
        if (existingExercise == null) {
            throw new NotFoundException("Can't update. Exercise with id = " + id + " does not exist.");
        }
        existingExercise.setName(exercise.getName());
        existingExercise.setDuration(exercise.getDuration());
        existingExercise.setDescription(exercise.getDescription());
        existingExercise.setImage(exercise.getImage());
        return exerciseRepository.save(existingExercise);
    }

    public String deleteExercise(int id) {
        Exercise existingExercise = exerciseRepository.findById(id);
        if (existingExercise == null) {
            throw new NotFoundException("Can't delete. Exercise with id = " + id + " does not exist.");
        }
        List<Training> trainingList = trainingRepository.findByExercises(existingExercise);
        for (Training training:trainingList) {
            training.getExercises().remove(existingExercise);
            trainingRepository.save(training);
        }
        exerciseRepository.deleteById(id);
        return "Exercise removed.";
    }

    public Exercise updateExercise(int id, Map<Object, Object> objectMap) {
        Exercise existingExercise = exerciseRepository.findById(id);
        if (existingExercise == null) {
            throw new NotFoundException("Can't update. Exercise with id = " + id + " does not exist.");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(TrainingHistory.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingExercise, value);
        });
        return exerciseRepository.save(existingExercise);
    }

    public long countExercises() {
        return exerciseRepository.count();
    }

    public List<Exercise> getExercisesForTraining(int trainingId) {
        Training training = trainingRepository.findById(trainingId);
        if (training == null) {
            throw new NotFoundException("Training does not exist");
        }
        return exerciseRepository.findByTrainings(training);
    }
}
