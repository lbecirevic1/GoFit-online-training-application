package com.GoFit.DietPlan.Rabbitmq;

import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.GoFit.DietPlan.Services.AthleteService;
import com.GoFit.DietPlan.Services.DietPlanService;
import com.GoFit.DietPlan.Services.PhysicalConditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitReceiver implements RabbitListenerConfigurer {
    private static final Logger logger= LoggerFactory.getLogger(RabbitReceiver.class);

    @Autowired
    private AthleteService athleteService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DietPlanRepository dietPlanRepository;
    @Autowired
    private DietPlanService dietPlanService;
    @Autowired
    private PhysicalConditionService physicalConditionService;
    @Autowired
    private PhysicalConditionRepository physicalConditionRepository;
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar){}
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void received(String poruka){
        logger.info("radi");
        System.out.println(poruka);
        String email2= poruka.substring(1,poruka.length()-1);
        User user=userRepository.findByEmail(email2).orElse(null);
        if(user==null){
            dietPlanService.notifyUser("noUserFound,"+email2);
        }
        else{
            PhysicalCondition physicalCondition=physicalConditionRepository.findPhysicalConditionByUser_Id(user.getId());
            if(physicalCondition==null){
                userRepository.delete(user);
                dietPlanService.notifyUser("deleted,"+email2);
            }
            else{
                DietPlan dietPlan=dietPlanRepository.findDietPlanByCondition_Id(physicalCondition.getId());
                if(dietPlan!=null){
                    dietPlanRepository.delete(dietPlan);
                    physicalConditionRepository.delete(physicalCondition);
                    userRepository.delete(user);
                    dietPlanService.notifyUser("deleted,"+email2);
                }
                else{
                    physicalConditionRepository.delete(physicalCondition);
                    userRepository.delete(user);
                    dietPlanService.notifyUser("deleted,"+email2);
                }

            }
        }



    }
}

