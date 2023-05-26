package com.GoFit.DietPlan.Services;

import com.GoFit.DietPlan.Exceptions.NotFoundException;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Requests.addUserRequest;
import com.GoFit.DietPlan.Requests.updateUserRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private List<User> recoveryListaUsera;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;


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

    public void addBackALllDeletedUsers(){
        for(User user :recoveryListaUsera){
            userRepository.save(user);
        }
    }

  /*  public String deleteUser(User user){
        userRepository.deleteByEmail(user.getEmail());
        return "User removed";
    }*/

    public void deleteUser(String email){
        rabbitTemplate.convertAndSend(exchange,routingKey,email);
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }
    public void notifyUser(String email){rabbitTemplate.convertAndSend(exchange,routingKey,email);}



}
