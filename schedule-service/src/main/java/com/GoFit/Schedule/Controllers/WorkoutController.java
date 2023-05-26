package com.GoFit.Schedule.Controllers;

import com.GoFit.Schedule.Feign.TrainingConsumer;
import com.GoFit.Schedule.Models.Schedule;
import com.GoFit.Schedule.Request.Training;
import com.GoFit.Schedule.Models.Workout;
import com.GoFit.Schedule.Request.WorkoutRequest;
import com.GoFit.Schedule.Services.ScheduleService;
import com.GoFit.Schedule.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/schedule")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainingConsumer trainingConsumer;


    // GET
    @GetMapping("/workout/{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable int id) {
        return new ResponseEntity<Workout>(workoutService.getWorkout(id), HttpStatus.OK);
    }

    @GetMapping("/workouts")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return new ResponseEntity<List<Workout>>(workoutService.getWorkouts(), HttpStatus.OK);
    }

//    @GetMapping("/workout/{id}/trainings")
//    public List<Integer> getTrainingsByWorkoutType(@PathVariable int id) {
//        try {
//            Workout workout = workoutService.getWorkout(id);
//            List<Integer> trainingsByType = trainingConsumer.getTrainingsByType(workout.getType());
//            return trainingsByType;
//        } catch (NotFoundException exception) {
//            throw exception;
//        }
//    }
//
//    @GetMapping("/workout/{id}/trainings")
//    public List<Integer> getTrainingsByWorkoutDuration(@PathVariable int id) {
//        try {
//            Workout workout = workoutService.getWorkout(id);
//            List<Integer> trainingsByType = trainingConsumer.getTrainingsByDuration(workout.getDuration());
//            return trainingsByType;
//        } catch (NotFoundException exception) {
//            throw exception;
//        }
//    }

    @GetMapping("/workout/trainings")
    public List<Training> getAllTrainings() {
        return trainingConsumer.getTrainings();
    }

    // POST
    @Transactional
    @PostMapping("/workout")
    public ResponseEntity<Workout> createWorkout(@RequestBody WorkoutRequest workoutRequest) {
        Workout workout = new Workout();
        workout.setTitle(workoutRequest.title);
        workout.setNote(workoutRequest.note);
        workout.setStart(workoutRequest.start);
        workout.setEnd(workoutRequest.end);
        workout.setTrainings(workoutRequest.trainings);
        Schedule schedule = scheduleService.getSchedule(workoutRequest.scheduleId);
        workout.setSchedule(schedule);



        Workout createdWorkout = workoutService.addWorkout(workout);

        scheduleService.addWorkout(schedule.getId(),createdWorkout);

        return new ResponseEntity<Workout>(createdWorkout, HttpStatus.CREATED);
    }

    @PostMapping("/workouts")
    public ResponseEntity<List<Workout>> createWorkouts(@Valid @RequestBody List<Workout> workouts) {
        return new ResponseEntity<List<Workout>>(workoutService.addWorkouts(workouts), HttpStatus.CREATED);

    }

    // PUT
    @PutMapping("/workout/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable int id, @RequestBody WorkoutRequest workout) {
        return new ResponseEntity<Workout>(workoutService.putWorkout(id, workout), HttpStatus.OK);
    }

    // PATCH
    @PatchMapping("/workout/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable int id, @Valid @RequestBody Map<Object, Object> objectMap) {
        return new ResponseEntity<Workout>(workoutService.updateWorkout(id, objectMap), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/workout/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable int id) {
        return new ResponseEntity<String>(workoutService.deleteWorkout(id), HttpStatus.OK);
    }
}
