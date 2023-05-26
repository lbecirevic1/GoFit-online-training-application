package com.GoFit.userservice.Controllers;

import com.GoFit.userservice.Models.Role;
import com.GoFit.userservice.Models.User;
import com.GoFit.userservice.Request.deleteUserRequest;
import com.GoFit.userservice.Services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.putUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return new ResponseEntity<String>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/{username}")
    public ResponseEntity<User> changeUsername(@PathVariable int id, @PathVariable String username) {
        return new ResponseEntity<User>(userService.changeUsername(id, username), HttpStatus.OK);
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody Map<Object, Object> objectMap) {
        return new ResponseEntity<User>(userService.updateUser(id, objectMap), HttpStatus.OK);
    }


    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        Role createdRole = userService.saveRole(role);
        return new ResponseEntity<Role>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        return new ResponseEntity<Long>(userService.countUsers(), HttpStatus.OK);
    }
    @GetMapping("/rabbitProba")
    public ResponseEntity<String> rabbitProba(){
        userService.sendUser("ode sa usera");
        return new ResponseEntity<>("nova poruka",HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserFromMS")
    public ResponseEntity<String> deleteUser(@RequestBody deleteUserRequest deleteUserRequest){
        userService.deleteUserFromMS(deleteUserRequest.getEmail());
        return new ResponseEntity<>("ode poruka da brise usera",HttpStatus.OK);
    }

}
