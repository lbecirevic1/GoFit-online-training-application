package com.GoFit.Training.Controllers;

import com.GoFit.Training.Exceptions.NotFoundException;
import com.GoFit.Training.Models.Exercise;
import com.GoFit.Training.Models.TrainingHistory;
import com.GoFit.Training.Services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/training")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    //POST (CREATE)
    @PostMapping("/exercise")
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody Exercise exercise) {
        return new ResponseEntity<Exercise>(exerciseService.saveExercise(exercise), HttpStatus.CREATED);
    }

    @PostMapping("/exercises")
    public ResponseEntity<List<Exercise>> createExercises(@Valid @RequestBody List<Exercise> exercises) {
        return new ResponseEntity<List<Exercise>>(exerciseService.saveExercises(exercises),HttpStatus.CREATED);
    }

    //GET (READ)
    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getExercises() {
        return new ResponseEntity<>(exerciseService.getExercises(), HttpStatus.OK);
    }

    @GetMapping("/exercise/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable int id) {
        return new ResponseEntity<>(exerciseService.getExerciseById(id), HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/exercise/put/{id}")
    public ResponseEntity<Exercise> putExercise(@PathVariable int id, @Valid @RequestBody Exercise exercise) {
        return new ResponseEntity<>(exerciseService.putExercise(id, exercise), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/exercise/delete/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable int id) {
        return new ResponseEntity<String>(exerciseService.deleteExercise(id), HttpStatus.OK);
    }

    @PatchMapping("/exercise/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable int id, @Valid @RequestBody Map<Object, Object> objectMap) {
        return new ResponseEntity<Exercise>(exerciseService.updateExercise(id, objectMap), HttpStatus.OK);
    }

    @GetMapping("/exercise/count")
    public ResponseEntity<Long> countExercises() {
        return new ResponseEntity<Long>(exerciseService.countExercises(), HttpStatus.OK);
    }

    @GetMapping("/exercisesForTraining/{id}")
    public ResponseEntity<List<Exercise>> getExercisesForTraining(@PathVariable int id) {
        return new ResponseEntity<List<Exercise>>(exerciseService.getExercisesForTraining(id), HttpStatus.OK);
    }
}

