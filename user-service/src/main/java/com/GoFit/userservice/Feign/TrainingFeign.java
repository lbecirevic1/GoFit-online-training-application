package com.GoFit.userservice.Feign;

import com.GoFit.userservice.Models.User;
import org.apache.catalina.connector.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@FeignClient(name = "training-service")
public interface TrainingFeign {

    @PostMapping("/training/user/create")
    public ResponseEntity<User> register(@RequestBody User user);

    @DeleteMapping("/training/user/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user);

}
