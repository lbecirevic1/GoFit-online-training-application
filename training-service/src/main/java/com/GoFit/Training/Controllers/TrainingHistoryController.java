package com.GoFit.Training.Controllers;


import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Models.TrainingHistory;
import com.GoFit.Training.Requests.TrainingHistoryRequest;
import com.GoFit.Training.Services.TrainingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/training")
public class TrainingHistoryController {

    @Autowired
    TrainingHistoryService trainingHistoryService;

    @PostMapping("/trainingHistory")
    public ResponseEntity<TrainingHistory> createTrainingHistory(@Valid @RequestBody TrainingHistoryRequest trainingHistoryRequest) {
       return new ResponseEntity<TrainingHistory>(trainingHistoryService.createTrainingHistory(trainingHistoryRequest), HttpStatus.OK);
    }

    @GetMapping("/trainingHistory/{id}")
    public ResponseEntity<TrainingHistory> getTrainingHistory(@PathVariable int id) {
        return new ResponseEntity<TrainingHistory>(trainingHistoryService.getTrainingHistory(id), HttpStatus.OK);
    }

    @GetMapping("/trainingHistories")
    public ResponseEntity<List<TrainingHistory>> getTrainingHistories() {
        return new ResponseEntity<List<TrainingHistory>>(trainingHistoryService.getTrainingHistories(), HttpStatus.OK);
    }

    @DeleteMapping("/trainingHistory/{id}")
    public ResponseEntity<String> deleteTrainingHistory(@PathVariable int id) {
        return new ResponseEntity<String>(trainingHistoryService.deleteTrainingHistory(id), HttpStatus.OK);
    }


    @PatchMapping("/trainingHistory/{id}")
    public ResponseEntity<TrainingHistory> updateTrainingHistory(@PathVariable int id, @Valid @RequestBody Map<Object, Object> objectMap) {
        return new ResponseEntity<TrainingHistory>(trainingHistoryService.updateTraining(id, objectMap), HttpStatus.OK);
    }

    @GetMapping("/trainingHistory/user/{email}")
    public ResponseEntity<List<Training>> getHistoryForUser(@PathVariable String email) {
        return new ResponseEntity<List<Training>>(trainingHistoryService.getHistoryForUser(email), HttpStatus.OK);
    }

    @GetMapping("/trainingHistory/vote/{trainingId}")
    public ResponseEntity<Double> getTrainingVote(@PathVariable int trainingId) {
        return new ResponseEntity<Double>(trainingHistoryService.getTrainingVote(trainingId), HttpStatus.OK);
    }


}
