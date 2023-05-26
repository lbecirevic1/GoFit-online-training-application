package com.GoFit.userservice.Services;

import com.GoFit.userservice.Exceptions.NotFoundException;
import com.GoFit.userservice.Feign.DietPlanFeign;
import com.GoFit.userservice.Feign.ScheduleFeign;
import com.GoFit.userservice.Feign.TrainingFeign;
import com.GoFit.userservice.Models.Role;
import com.GoFit.userservice.Models.TemporaryUser;
import com.GoFit.userservice.Models.User;
import com.GoFit.userservice.Repositories.RoleRepository;
import com.GoFit.userservice.Repositories.TemporaryUserRepository;
import com.GoFit.userservice.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TrainingFeign trainingFeign;

    @Autowired
    TemporaryUserRepository temporaryUserRepository;

    @Autowired
    DietPlanFeign dietPlanFeign;

    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Autowired
    public UserService(RabbitTemplate rabbitTemplate){this.rabbitTemplate=rabbitTemplate;}

    @Autowired
    ScheduleFeign scheduleFeign;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("User with id = " + id + " does not exist.");
        }
        return user;
    }

    public User putUser(int id, User user) {
        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            throw new NotFoundException("Can't update. User with id = " + id + " does not exist.");
        }
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public String deleteUser(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("Can't delete. User with id = " + id + " does not exist.");
        }
        trainingFeign.deleteUser(new User(user.getName(), user.getEmail()));
        dietPlanFeign.deleteUser(new User(user.getName(),user.getEmail()));
        scheduleFeign.deleteUser(new User(user.getName(),user.getEmail()));
        userRepository.deleteById(id);
        return "User removed.";
    }

    public User changeUsername(int id, String username) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("Can't update. User with id = " + id + " does not exist.");
        }
        user.setEmail(username);
        return userRepository.save(user);
    }


    public User updateUser(int id, Map<Object, Object> objectMap) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("Can't update. User with id = " + id + " does not exist.");
        }
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, user, value);
        });
        return userRepository.save(user);
    }



    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Long countUsers() {
        return userRepository.count();
    }

//    public void addRoleToUser(String email, String roleName) {
//        User user = userRepository.findByEmail(email);
//        Role role = roleRepository.findByName(roleName);
//        user.getRoles().add(role);
////        userRepository.save(user);
//    }

    public void sendUser(String poruka){
        rabbitTemplate.convertAndSend(exchange,routingKey,poruka);
    }

    public void deleteUserFromMS(String email){
        User user=userRepository.findUserByEmail(email);
        userRepository.delete(user);
        TemporaryUser temporaryUser=new TemporaryUser(user.getName(),user.getLastName(),user.getUsername(),user.getPassword(),user.getEmail(),user.getAge());
        temporaryUserRepository.save(temporaryUser);
        rabbitTemplate.convertAndSend(exchange,routingKey,email);
    }

}
