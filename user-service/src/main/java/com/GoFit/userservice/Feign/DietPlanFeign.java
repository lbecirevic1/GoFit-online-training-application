package com.GoFit.userservice.Feign;

import com.GoFit.userservice.Models.User;
import org.apache.catalina.connector.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@FeignClient(name = "dietplan-service")
public interface DietPlanFeign {

    @PostMapping("/dietplan/user/create")
    public ResponseEntity<User> registerUser(@RequestBody User user);

    @DeleteMapping("/dietplan/user/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user);

}
