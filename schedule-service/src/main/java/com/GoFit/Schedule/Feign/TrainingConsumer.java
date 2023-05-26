package com.GoFit.Schedule.Feign;

import com.GoFit.Schedule.Request.Training;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import com.dev.springcloud.feign.model.Training;

import java.util.List;

@FeignClient(name = "training-service")
public interface TrainingConsumer {

    @GetMapping("/training/trainings")
    public List<Training> getTrainings();

    @GetMapping("/training/{ids}")
    public List<Training> getTrainingsByIds(@PathVariable String ids);

//    @GetMapping("/listOfTrainings/user/{userId}")
//    public List<Integer> getTrainingsForUser(@PathVariable int userId);

//    @GetMapping("/trainingsType/{type}")
//    public List<Integer> getTrainingsByType(@PathVariable String type);

//    @GetMapping("/trainingsDuration/{duration}")
//    public List<Integer> getTrainingsByDuration(@PathVariable Double duration);
}
