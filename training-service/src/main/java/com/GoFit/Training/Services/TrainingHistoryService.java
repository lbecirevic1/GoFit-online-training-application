package com.GoFit.Training.Services;

import com.GoFit.Training.Exceptions.NotFoundException;
import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Models.TrainingHistory;
import com.GoFit.Training.Models.User;
import com.GoFit.Training.Repositories.TrainingHistoryRepository;
import com.GoFit.Training.Repositories.TrainingRepository;
import com.GoFit.Training.Repositories.UserRepository;
import com.GoFit.Training.Requests.TrainingHistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class TrainingHistoryService {
    @Autowired
    TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainingRepository trainingRepository;

    public TrainingHistory createTrainingHistory(TrainingHistoryRequest trainingHistoryRequest) {
        User user = userRepository.findByEmail(trainingHistoryRequest.email);
        Training training = trainingRepository.findById(trainingHistoryRequest.trainingId);

        TrainingHistory trainingHistory = new TrainingHistory(trainingHistoryRequest.vote);
        trainingHistory.setTraining(training);
        trainingHistory.setUser(user);
        user.getTrainingHistory().add(trainingHistory);
        training.getTrainingHistory().add(trainingHistory);
        //training.addTrainingHistory(trainingHistory);
        return trainingHistoryRepository.save(trainingHistory);
    }

    public TrainingHistory getTrainingHistory(int id) {
        TrainingHistory existingTrainingHistory = trainingHistoryRepository.findById(id);
        if (existingTrainingHistory == null) {
            throw new NotFoundException("Training History with id = " + id + " does not exist.");
        }
        return existingTrainingHistory;
    }

    public List<TrainingHistory> getTrainingHistories() {
        return trainingHistoryRepository.findAll();
    }

    public String deleteTrainingHistory(int id) {
        TrainingHistory existingTrainingHistory = trainingHistoryRepository.findById(id);
        if (existingTrainingHistory == null) {
            throw new NotFoundException("Can't delete. Training History with id = " + id + " does not exist.");
        }
        trainingHistoryRepository.deleteById(id);
        return "Training History removed.";
    }


    public TrainingHistory updateTraining(int id, Map<Object, Object> objectMap) {
        TrainingHistory existingTrainingHistory = trainingHistoryRepository.findById(id);
        if (existingTrainingHistory == null) {
            throw new NotFoundException("Can't update. Training History with id = " + id + " does not exist.");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(TrainingHistory.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingTrainingHistory, value);
        });
        return trainingHistoryRepository.save(existingTrainingHistory);
    }

    public List<Training> getHistoryForUser(String email) {
        User user = userRepository.findByEmail(email);
        List<TrainingHistory> trainingHistoryList = trainingHistoryRepository.findTrainingHistoriesByUser(user);
        List<Training> trainingList = new ArrayList<>();

        for (TrainingHistory trainingHistory : trainingHistoryList) {
            Training training = trainingRepository.findById(trainingHistory.getTraining().getId());
            trainingList.add(training);
        }

        List<Training> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(trainingList));
        return listWithoutDuplicates;
    }

    public Double getTrainingVote(int trainingId) {
        Training training = trainingRepository.findById(trainingId);
        List<TrainingHistory> trainingHistoryList = trainingHistoryRepository.findTrainingHistoriesByTraining(training);
        List<TrainingHistory> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(trainingHistoryList));

        return listWithoutDuplicates.get(0).getVote();
    }
}
