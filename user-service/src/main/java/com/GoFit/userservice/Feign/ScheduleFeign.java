package com.GoFit.userservice.Feign;

import com.GoFit.userservice.Models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schedule-service")
public interface ScheduleFeign {
    @PostMapping("/schedule/user/create")
    public ResponseEntity<User> registerUser(@RequestBody User user);

    @DeleteMapping("/schedule/user/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user);
}
