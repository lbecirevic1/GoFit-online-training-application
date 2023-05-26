package com.GoFit.Schedule.Services;

import com.GoFit.Schedule.Exceptions.NotFoundException;
import com.GoFit.Schedule.Models.User;
import com.GoFit.Schedule.Models.Schedule;
import com.GoFit.Schedule.Models.Workout;
import com.GoFit.Schedule.Repositories.UserRepository;
import com.GoFit.Schedule.Repositories.ScheduleRepository;
import com.GoFit.Schedule.Repositories.WorkoutRepository;
import com.GoFit.Schedule.Request.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    public Schedule addSchedule(ScheduleRequest schedule) {
        Schedule addSchedule = new Schedule();
        try {
            User user = userRepository.findByEmail(schedule.email);
            System.out.println("useaefsefdr"+ user.getEmail());
            if(user != null) {
                List<Workout> workouts = workoutRepository.findAllById(schedule.workouts);
                if(user != null) {
                    addSchedule.setTitle("Schedule for " + user.getName());
                    addSchedule.setUser(user);
//                    if(workouts.size() > 0) {
//                        addSchedule.setWorkouts(workouts.stream().collect(Collectors.toSet()));
//                    } else {
//                        addSchedule.setWorkouts(new HashSet<>());
//                    }
                }
                return scheduleRepository.save(addSchedule);
            }
            return null;

        } catch(NotFoundException exception) {
            throw exception;
        }

    }

    public List<Schedule> addSchedules(List<Schedule> scheduleList) {
        return scheduleRepository.saveAll(scheduleList);
    }

    public Schedule addWorkout(int id, Workout workout) {
        Schedule schedule = scheduleRepository.findById(id);
//        schedule.addWorkout(workout);
        return scheduleRepository.save(schedule);
    }

    public Schedule getSchedule(int id) {
        Schedule schedule = scheduleRepository.findById(id);
        if (schedule == null) {
            throw new NotFoundException("Schedule with id = " + id + " does not exist");
        }
        return schedule;
    }

    public Schedule getScheduleByUserId(String id) {
        User user = userRepository.findByEmail(id);
        if(user != null) {

            List<Schedule> schedules = scheduleRepository.findAll()
                    .stream()
                    .filter(s -> s.getUser().getEmail().equals(user.getEmail()))
                    .collect(Collectors.toList());
            if (schedules.size() >= 1) {
                return schedules.get(0);
            } else {
                throw new NotFoundException("Schedule for user with id = " + id + " does not exist");
            }
        }
        throw new NotFoundException("User with id = " + id + " does not exist");
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

//    public Schedule putSchedule(int id, Schedule schedule) {
//        Schedule updateSchedule = scheduleRepository.findById(id);
//        if (updateSchedule == null) {
//            throw new NotFoundException("Schedule with id = " + id + " does not exist");
//        }
//        updateSchedule.setUser(schedule.getUser());
//        updateSchedule.setDateRange1(schedule.getDateRange1());
//        updateSchedule.setDateRange2(schedule.getDateRange2());
//        updateSchedule.setWorkouts(schedule.getWorkouts());
//        return scheduleRepository.save(updateSchedule);
//    }

    public String deleteSchedule(int id) {
        Schedule schedule = scheduleRepository.findById(id);
        if (schedule == null) {
            throw new NotFoundException("Schedule with id = " + id + " does not exist");
        }
        scheduleRepository.deleteById(id);
        return "Schedule with id = " + id + " was successfully removed.";
    }

    public Schedule updateSchedule(int id, Map<Object, Object> objectMap) {
        Schedule existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule == null) {
            throw new NotFoundException("Can't update. Schedule with id = " + id + " does not exist.");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Schedule.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingSchedule, value);
        });
        return scheduleRepository.save(existingSchedule);
    }
}
