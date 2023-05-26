package com.GoFit.Training.Repositories;

import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Models.TrainingHistory;
import com.GoFit.Training.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Integer> {
    public TrainingHistory findById(int id);
    public List<TrainingHistory> findTrainingHistoriesByUser(User user);

    public List<TrainingHistory> findTrainingHistoriesByTraining(Training training);

    public TrainingHistory findTrainingHistoryByTrainingAndUser(Training training, User user);
//    public List<TrainingHistory> findByTrainedAtAndCalories();
}
