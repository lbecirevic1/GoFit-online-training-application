package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Exceptions.NotFoundException;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Requests.addUserRequest;
import com.GoFit.DietPlan.Requests.deleteUserRequest;
import com.GoFit.DietPlan.Requests.updateUserRequest;
import com.GoFit.DietPlan.Services.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dietplan/user")
public class UserController {

        @Autowired
        private AthleteService userService;

        @PostMapping("/createUsers")
        public List<User> addUsers(@Valid @RequestBody List<User> users) {
            return userService.saveUsers(users);
        }

        @GetMapping("/users")
        public ResponseEntity<List<User>>getUsers(){

           return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
        }


        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable int id) {
            return new ResponseEntity<String>(userService.deleteUser(id),HttpStatus.OK);
        }

      /*  @DeleteMapping("/delete")
        public ResponseEntity<String> deleteUser(@RequestBody User user){
            return new ResponseEntity<String>(userService.deleteUser(user),HttpStatus.OK);
        }*/

        @PostMapping("/create")
        public ResponseEntity<User> registerUser(@RequestBody User user){
            return new ResponseEntity<User>(userService.registerUser(user),HttpStatus.CREATED);
        }

      /*  @DeleteMapping("/deleteUser")
        public ResponseEntity<String> updateUsername(@RequestBody deleteUserRequest deleteUserRequest){
                userService.deleteUser(deleteUserRequest.getEmail());
                return new ResponseEntity<String>("poslano",HttpStatus.OK);
        }*/

        @DeleteMapping("/deleteUser")
        public ResponseEntity<String> deleteUser(@RequestBody deleteUserRequest deleteUserRequest){
                userService.notifyUser(deleteUserRequest.getEmail());
                return new ResponseEntity<String>("poslano",HttpStatus.OK);
        }


}

