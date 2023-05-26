package com.GoFit.Schedule.Services;

import com.GoFit.Schedule.Exceptions.NotFoundException;
import com.GoFit.Schedule.Models.Workout;
import com.GoFit.Schedule.Repositories.WorkoutRepository;
import com.GoFit.Schedule.Repositories.WorkoutRepository;
import com.GoFit.Schedule.Request.WorkoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Transactional
    public Workout addWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public List<Workout> addWorkouts(List<Workout> workoutList) {
        return workoutRepository.saveAll(workoutList);
    }

    public Workout getWorkout(int id) {
        Workout workout = workoutRepository.findById(id);
        if (workout == null) {
            throw new NotFoundException("Workout with id = " + id + " does not exist");
        }
        return workout;
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

//    public Workout putWorkout(int id, Workout workout) {
//        Workout updateWorkout = workoutRepository.findById(id);
//        if (updateWorkout == null) {
//            throw new NotFoundException("Workout with id = " + id + " does not exist");
//        }
//        updateWorkout.setDateTime(workout.getDateTime());
//        updateWorkout.setDuration(workout.getDuration());
//        updateWorkout.setNote(workout.getNote());
//        updateWorkout.setSchedules(workout.getSchedules());
//        return workoutRepository.save(updateWorkout);
//    }

    public String deleteWorkout(int id) {
        Workout workout = workoutRepository.findById(id);
        if (workout == null) {
            throw new NotFoundException("Workout with id = " + id + " does not exist");
        }
        workoutRepository.deleteById(id);
        return "Workout with id = " + id + " was successfully removed.";
    }

    public Workout putWorkout(int id, WorkoutRequest workoutRequest) {
        Workout find = workoutRepository.findById(id);
        if(find!= null) {
            Workout update = new Workout();
            update.setSchedule(find.getSchedule());
            update.setId(id);
            update.setTrainings(workoutRequest.trainings);
            update.setStart(workoutRequest.start);
            update.setEnd(workoutRequest.end);
            update.setTitle(workoutRequest.title);
            update.setNote(workoutRequest.note);

            return workoutRepository.save(update);
        }
        throw new NotFoundException("workout with id " + id + " not found, you cannot edit it");
    }

    public Workout updateWorkout(int id, Map<Object, Object> objectMap) {
        Workout existingWorkout = workoutRepository.findById(id);
        if (existingWorkout == null) {
            throw new NotFoundException("Can't update. Workout with id = " + id + " does not exist.");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Workout.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingWorkout, value);
        });
        return workoutRepository.save(existingWorkout);
    }
}
