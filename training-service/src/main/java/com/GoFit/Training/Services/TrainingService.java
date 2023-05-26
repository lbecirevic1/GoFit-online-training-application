package com.GoFit.Training.Services;

import com.GoFit.Training.Exceptions.NotFoundException;
import com.GoFit.Training.Models.Exercise;
import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Requests.TrainingRequest;
import com.GoFit.Training.Repositories.ExerciseRepository;
import com.GoFit.Training.Repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public TrainingService() {
    }

    //Logic for POST
    public Training addTraining(TrainingRequest trainingRequest) {
        Training training = new Training();
        training.setDuration(trainingRequest.duration);
        training.setType(trainingRequest.type);
        training.setName(trainingRequest.name);
        training.setTargetArea(trainingRequest.targetArea);
        training.setVideoLink(trainingRequest.videoLink);
        training.setCoverImage(trainingRequest.coverImage);
        training.setDescription(trainingRequest.description);
        Set<Exercise> exerciseList = exerciseRepository.findByIdIn(trainingRequest.exercises);

        training.setExercises(exerciseList.stream().map(
                 exercise -> {
                     Exercise _exercise = exercise;
                     //Check if it is already in database
                     if(_exercise.getId() > 0) {
                         //Pull all the trainings which have been mapped with this exercise
                         _exercise = exerciseRepository.findById(_exercise.getId());
                     }
                     //Fetch all trainings and add training
                     _exercise.addTraining(training);
                     return _exercise;
                 }
         ).collect(Collectors.toSet()));
         return trainingRepository.save(training);
    }

//    public Training addTraining(TrainingRequest trainingRequest) {
//        Training training = new Training();
//        training.setDuration(trainingRequest.duration);
//        training.setType(trainingRequest.type);
//        training.setName(trainingRequest.name);
//        training.setTargetArea(trainingRequest.targetArea);
//        training.setVideoLink(trainingRequest.videoLink);
//        training.setCoverImage(trainingRequest.coverImage);
//        training.setDescription(trainingRequest.description);
//
//        List<Exercise> exerciseList = exerciseRepository.findByIdIn(trainingRequest.exercises);
//        training.setExercises(exerciseList);
//        return trainingRepository.save(training);
//    }


    //Logic for GET
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    public Training getTrainingById(int id) {
        Training existingTraining = trainingRepository.findById(id);
        if (existingTraining == null) {
            throw new NotFoundException("Training with id = " + id + " does not exist.");
        }
        return existingTraining;
    }

    public List<Training> getByType(String type) {
        return trainingRepository.findByType(type);
    }

    public List<Training> getByDuration(Double duration) {
        return trainingRepository.findByDuration(duration);
    }

    public List<Training> getByDurationAndType(Double duration, String type) {
        return trainingRepository.findByDurationAndType(duration, type);
    }


    public Training putTraining(int id, Training training) {
        Training existingTraining = trainingRepository.findById(id);
        if (existingTraining == null) {
            throw new NotFoundException("Can't update. Training with id = " + id + " does not exist.");
        }
        existingTraining.setName(training.getName());
        existingTraining.setDuration(training.getDuration());
        existingTraining.setType(training.getType());
        existingTraining.setExercises(training.getExercises());
        return trainingRepository.save(existingTraining);
    }

    //Logic for DELETE
    public String deleteTraining(int id) {
        Training existingTraining = trainingRepository.findById(id);
        if (existingTraining == null) {
            throw new NotFoundException("Can't delete. Training with id = " + id + " does not exist.");
        }
        trainingRepository.deleteById(id);
        return "Training removed.";
    }


    public Training updateTraining(int id, Map<Object, Object> objectMap) {
        Training existingTraining = trainingRepository.findById(id);
        if (existingTraining == null) {
            throw new NotFoundException("Can't update. Training with id = " + id + " does not exist.");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Training.class, (String) key);
            field.setAccessible(true);
//            if (((String)key).equals("exercises")) {
//                value = Set.copyOf((List) value);
//            }
            ReflectionUtils.setField(field, existingTraining, value);
        });
        return trainingRepository.save(existingTraining);
    }

    public Long countTraining() {
       return trainingRepository.count();
    }

    public List<Training> getByTargetArea(String targetArea) {
        return trainingRepository.findTrainingsByTargetArea(targetArea);
    }

    public List<String>[] filterTargetArea() {
        return trainingRepository.findTargetArea();
    }

    public List<String>[] filterType() {
        return trainingRepository.findType();
    }
}
