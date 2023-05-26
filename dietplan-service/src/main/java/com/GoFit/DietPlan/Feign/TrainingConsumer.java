package com.GoFit.DietPlan.Feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "training-service")
public interface TrainingConsumer {

    @GetMapping("/trainingHistory/{id}/{date}")
    public Double getCaloriesByDate(@PathVariable int id,@PathVariable String date);
}