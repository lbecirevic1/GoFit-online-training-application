package com.GoFit.Schedule.Services;

import com.GoFit.Schedule.Exceptions.NotFoundException;
import com.GoFit.Schedule.Models.User;
import com.GoFit.Schedule.Repositories.UserRepository;
import com.GoFit.Schedule.Repositories.ScheduleRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository ScheduleRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private List<User> recoveryListaUsera;

    // @Autowired
    // UserServiceConsumer userServiceConsumer;


    /*  public User saveUser(addUserRequest user){
          User u=new User();
          u.setPassword(user.password);
          u.setEmail(user.email);
         // u.setBirthday(user.birthday);
          u.setUsername(user.displayName);
          u.setLastName(user.lastName);
          u.setName(user.name);
          return userRepository.save(u);
      }
  */
    public List<User> saveUsers(List<User> users){
        return userRepository.saveAll(users);
    }
    public List<User> getUsers() {
        List<User>atlete=userRepository.findAll();

        return atlete;
    }

    public User getUserById(int id) {
        User u=userRepository.findById(id).orElse(null);
        if(u==null){
            throw new NotFoundException("User with id = " + id + " does not exist.");
        }
        return u;
    }

    //Logic for UPDATE
  /*  public User updateUser (int id, updateUserRequest updateUser){

        User u=userRepository.findById(id).orElse(null);

        if(u!=null){
            u.setName(updateUser.name);
            u.setLastName(updateUser.lastName);
          //  u.setBirthday(updateUser.birthday);
            u.setEmail(updateUser.email);
            u.setPassword(updateUser.password);
            u.setUsername(updateUser.displayName);
            return userRepository.save(u);
        }
        throw new NotFoundException("User with id = " + id + " does not exist.");
    }*/

    //Logic for DELETE
    public String deleteUser(int id) {
        User user =userRepository.findById(id).orElse(null);
        if(user ==null){
            throw new NotFoundException("User with id = " + id + " does not exist.");
        }
        recoveryListaUsera.add(user);
        userRepository.deleteById(id);
        return "User removed";
    }


    public User getUserByName(String name) {
        User users =  userRepository.findByName(name);
        return users;
    }

 /*   public User getUserByDisplayName(String name) {
        User users= userRepository.findByUsername(name);
        if(users==null){
            throw new NotFoundException("There is no user with given name!");

        }
        return users;
    }
*/
   /* public int getUserByNameAndLastName(String name,String lastName){
        List<User> usersByName=userRepository.findByName(name);
        for (User user :usersByName) {
            String prezime2= user.getLastName();
            if(prezime2.equals(lastName)){
                int id= user.getId();
                return id;
            }

        }
       return 0;
    }*/

    /*public List<User> getUserByLastName(String name){
        List<User> users = (List<User>) userRepository.findByLastName(name);
        if(users.isEmpty()){
            throw new NotFoundException("There is no user with given name!");
        }
        return users;
    }*/

    public void addBackALllDeletedUsers(){
        for(User user :recoveryListaUsera){
            userRepository.save(user);
        }
    }

    public String deleteUser(User user){
        userRepository.deleteByEmail(user.getEmail());
        return "User removed";
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }



}