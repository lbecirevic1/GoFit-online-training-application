package com.GoFit.userservice.Services;

import com.GoFit.userservice.Exceptions.NotFoundException;
import com.GoFit.userservice.Feign.DietPlanFeign;
import com.GoFit.userservice.Feign.ScheduleFeign;
import com.GoFit.userservice.Feign.TrainingFeign;
import com.GoFit.userservice.Login.EmailRequest;
import com.GoFit.userservice.Login.LoginRequest;
import com.GoFit.userservice.Models.Role;
import com.GoFit.userservice.Models.User;
import com.GoFit.userservice.Repositories.RoleRepository;
import com.GoFit.userservice.Repositories.UserRepository;
import com.GoFit.userservice.Security.JWTUtils;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TrainingFeign trainingFeign;

    @Autowired
    DietPlanFeign dietPlanFeign;

    @Autowired
    ScheduleFeign scheduleFeign;

    @Autowired
    private JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    public Map<String, String> login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.email);
        if (user == null) {
            throw new NotFoundException("Account does not exists. Please register first.");
        }
        Boolean passwordMatches = passwordEncoder.matches(loginRequest.password, user.getPassword());
        if (!passwordMatches) {
            throw new NotFoundException("Password does not match.");
        }
        String token = jwtUtils.generateToken(user);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", token);
        return tokens;
    }

    public User register(User user) {
        System.out.println(user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        trainingFeign.register(new User(user.getName(), user.getEmail()));
        dietPlanFeign.registerUser(new User(user.getName(), user.getEmail()));
        scheduleFeign.registerUser(new User(user.getName(), user.getEmail()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User addAdmin(User user) {
        System.out.println(user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dietPlanFeign.registerUser(new User(user.getName(), user.getEmail()));
        scheduleFeign.registerUser(new User(user.getName(), user.getEmail()));
        trainingFeign.register(new User(user.getName(), user.getEmail()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.getRoles().add(role);
        return userRepository.save(user);
    }


    public String getResetLink(EmailRequest emailRequest) {
        System.out.println(emailRequest.email);
        User user = userRepository.findUserByEmail(emailRequest.email);
        System.out.println(user);
        if (user == null)
            throw new NotFoundException("Invalid email address");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.email);
        message.setText("Hello,\n" +
                " \n" +
                "You recently took steps to reset the password for your GoFit account. " +
                "Click on the link below to reset your password. \n" +
                "http://localhost:3000/changePassword");
        message.setSubject("GoFit");
        message.setFrom("gofitnwt@gmail.com");

        javaMailSender.send(message);
        return "Link sent";
    }

    public ResponseEntity<Object> changePassword(String email, String newPassword) {
        Map<String, String> map = new HashMap<>();

        User user = userRepository.findUserByEmail(email);
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodedNewPassword);
        userRepository.save(user);

        map.put("message", "Successfully changed password");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
