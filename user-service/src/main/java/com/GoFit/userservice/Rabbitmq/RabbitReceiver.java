package com.GoFit.userservice.Rabbitmq;

import com.GoFit.userservice.Feign.DietPlanFeign;
import com.GoFit.userservice.Models.TemporaryUser;
import com.GoFit.userservice.Models.User;
import com.GoFit.userservice.Repositories.TemporaryUserRepository;
import com.GoFit.userservice.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class RabbitReceiver {
    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DietPlanFeign dietPlanFeign;

    @Autowired
    private TemporaryUserRepository temporaryUserRepository;

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receives(String poruka){
        String[] poruka2=poruka.split(",");
        String message=poruka2[0].substring(1,poruka2[0].length());
        String email=poruka2[1].substring(0,poruka.length()-1-poruka2[0].length()-1);
        System.out.println(email);
        System.out.println(message);

        if(message.equals("deleted")){
            TemporaryUser temporaryUser=temporaryUserRepository.findByEmail(email);
            if(temporaryUser!=null) {
                temporaryUserRepository.delete(temporaryUser);
            }
        }
        else if(message.equals("noUserFound")){
            TemporaryUser temporaryUser=temporaryUserRepository.findByEmail(email);
            if(temporaryUser!=null) {
                User user=new User(temporaryUser.getName(),temporaryUser.getLastName(),temporaryUser.getUsername(),passwordEncoder.encode(temporaryUser.getPassword()),temporaryUser.getEmail(),temporaryUser.getAge());
                userRepository.save(user);
                dietPlanFeign.registerUser(new User(temporaryUser.getName(),temporaryUser.getEmail()));
                temporaryUserRepository.delete(temporaryUser);
            }
        }


    }
}