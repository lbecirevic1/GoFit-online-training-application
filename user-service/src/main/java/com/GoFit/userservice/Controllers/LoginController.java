package com.GoFit.userservice.Controllers;

import com.GoFit.userservice.Login.EmailRequest;
import com.GoFit.userservice.Login.LoginRequest;
import com.GoFit.userservice.Models.User;
import com.GoFit.userservice.Security.JWTUtils;
import com.GoFit.userservice.Services.LoginService;
import com.GoFit.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        return new ResponseEntity<User>(loginService.register(user), HttpStatus.CREATED);
    }

    @PostMapping(path = "/resetLink")
    public String getResetLink(@RequestBody EmailRequest email){
        return loginService.getResetLink(email);
    }

    @PutMapping(path = "/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody String oldPassword,@RequestBody String newPassword){
        return loginService.changePassword(oldPassword, newPassword);
    }
}
