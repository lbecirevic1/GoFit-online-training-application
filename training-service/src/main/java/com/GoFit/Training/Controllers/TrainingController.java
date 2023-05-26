package com.GoFit.Training.Controllers;

import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Requests.TrainingRequest;
import com.GoFit.Training.Services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("/training")
    public ResponseEntity<Training> createTraining(@Valid @RequestBody TrainingRequest trainingRequest) {
        return new ResponseEntity<>(trainingService.addTraining(trainingRequest), HttpStatus.CREATED);
    }

    @GetMapping("/trainings")
    public ResponseEntity<List<Training>> getTrainings () {
        return new ResponseEntity<>(trainingService.getTrainings(), HttpStatus.OK);
    }

    @GetMapping("/training/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable int id) {
        return new ResponseEntity<>(trainingService.getTrainingById(id), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Training>> getByType(@PathVariable String type) {
        return new ResponseEntity<>(trainingService.getByType(type), HttpStatus.OK);
    }

    @GetMapping("/targetArea/{targetArea}")
    public ResponseEntity<List<Training>> getByTargetArea(@PathVariable String targetArea) {
        return new ResponseEntity<List<Training>>(trainingService.getByTargetArea(targetArea), HttpStatus.OK);
    }

    @GetMapping("/trainingsDuration/{duration}")
    public ResponseEntity<List<Training>> getByDuration(@PathVariable Double duration) {
        return new ResponseEntity<>(trainingService.getByDuration(duration), HttpStatus.OK);
    }

    @GetMapping("/trainingsDurationType/{duration}/{type}")
    public ResponseEntity<List<Training>> getByDurationAndType(@PathVariable Double duration, @PathVariable String type) {
        return new ResponseEntity<>(trainingService.getByDurationAndType(duration, type), HttpStatus.OK);
    }

    @PutMapping("/training/put/{id}")
    public ResponseEntity<Training> putTraining(@PathVariable int id, @Valid @RequestBody Training training) {
        return new ResponseEntity<>(trainingService.putTraining(id, training), HttpStatus.OK);
    }

    @DeleteMapping("/training/delete/{id}")
    public ResponseEntity<String> deleteTraining(@PathVariable int id) {
        return new ResponseEntity<>(trainingService.deleteTraining(id) , HttpStatus.OK);
    }


    @PatchMapping("/training/{id}")
    public ResponseEntity<Training> updateUser(@PathVariable int id, @Valid @RequestBody Map<Object, Object> objectMap) {
        return new ResponseEntity<Training>(trainingService.updateTraining(id, objectMap), HttpStatus.OK);
    }

    @GetMapping("/training/count")
    public ResponseEntity<Long> countTraining() {
        return new ResponseEntity<Long>(trainingService.countTraining(), HttpStatus.OK);
    }

    @GetMapping("/filterTargetArea")
    public ResponseEntity<List<String>[]> filterTargetArea() {
        return new ResponseEntity<List<String>[]>(trainingService.filterTargetArea(), HttpStatus.OK);
    }

    @GetMapping("/filterType")
    public ResponseEntity<List<String>[]> filterType() {
        return new ResponseEntity<List<String>[]>(trainingService.filterType(), HttpStatus.OK);
    }
}
