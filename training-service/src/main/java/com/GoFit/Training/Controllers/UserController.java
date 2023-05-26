package com.GoFit.Training.Controllers;


import com.GoFit.Training.Models.User;
import com.GoFit.Training.Services.UserService;
//import com.github.fge.jsonpatch.JsonPatch;
//import com.github.fge.jsonpatch.JsonPatchException;
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
@RequestMapping("/training/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<User>(userService.register(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        return new ResponseEntity<String>(userService.deleteUser(user), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> deleteUser() {
        return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
    }

}
