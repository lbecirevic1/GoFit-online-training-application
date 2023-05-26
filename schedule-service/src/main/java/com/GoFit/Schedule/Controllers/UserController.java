package com.GoFit.Schedule.Controllers;

import com.GoFit.Schedule.Exceptions.NotFoundException;
import com.GoFit.Schedule.Models.User;
import com.GoFit.Schedule.Services.AthleteService;
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
@RequestMapping("/schedule/user")
public class UserController {

    @Autowired
    private AthleteService userService;

    private int duration;

    @Autowired
    private ConfigurableApplicationContext ctx;

    //POST (CREATE)
     /*   @PostMapping("/createUser")
        public User addUser(@Valid @RequestBody addUserRequest addUser) {
            return userService.saveUser(addUser);
        }
*/
    @PostMapping("/createUsers")
    public List<User> addUsers(@Valid @RequestBody List<User> users) {
        return userService.saveUsers(users);
    }

    //GET (READ)
    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){

        return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
    }

        /*@GetMapping("/athlete/{id}")
        public Athlete getUserById(@PathVariable int id) {
            return userService.getUserById(id);
        }*/

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

      /*  @GetMapping("/Name/{name}")
        public ResponseEntity<List<User>> getUserByName(@PathVariable String name){
            return new ResponseEntity<List<User>>(userService.getUserByName(name),HttpStatus.OK);
        }*/

     /*   @GetMapping("/LastName/{lastName}")
        public List<User> getUserByLastName(@PathVariable String lastName){
            return userService.getUserByLastName(lastName);
        }*/

    /*    @GetMapping("/DisplayName/{name}")
        public User getUserByDisplayName(@PathVariable String name){
            return userService.getUserByDisplayName(name);
        }*/

    /*    @GetMapping("/userByName/{name}/{lastName}")
        public User getUserByNameAndLastName(@PathVariable String name, @PathVariable String lastName){
            int id=userService.getUserByNameAndLastName(name,lastName);
            return userService.getUserById(id);
        }*/
    //UPDATE
     /*   @PutMapping("/update/{id}")
        public User updateUser(@PathVariable int id, @Valid @RequestBody updateUserRequest updateUser) {
            return userService.updateUser(id,updateUser);
        }*/

    /*    @PutMapping("/updateUserName/{name}/{lastName}")
        public User updateUser(@PathVariable String name, @PathVariable String lastName, @Valid @RequestBody updateUserRequest updateUser) {
            int id=userService.getUserByNameAndLastName(name,lastName);
            return userService.updateUser(id,updateUser);
        }*/

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return new ResponseEntity<String>(userService.deleteUser(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        return new ResponseEntity<String>(userService.deleteUser(user),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.registerUser(user),HttpStatus.CREATED);
    }

     /*   @DeleteMapping("/userByName/{name}/{lastName}")
        public String deleteUserByName(@PathVariable String name,@PathVariable String lastName){
            int id=userService.getUserByNameAndLastName(name,lastName);
            if(id!=0){
                return userService.deleteUser(id);
            }
            else throw new NotFoundException("nema");

        }*/



}
