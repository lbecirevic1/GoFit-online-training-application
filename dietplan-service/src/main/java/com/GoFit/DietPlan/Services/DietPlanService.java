package com.GoFit.DietPlan.Services;

import com.GoFit.DietPlan.Exceptions.NotFoundException;
import com.GoFit.DietPlan.Models.Recepie;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.GoFit.DietPlan.Repositories.RecepieRepository;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Requests.addDietPlanRequest;
import com.GoFit.DietPlan.Requests.updateDietPlanRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietPlanService {
    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecepieRepository recepieRepository;

    @Autowired
    private RecepieService recepieService;

    @Autowired
    private PhysicalConditionRepository physicalConditionRepository;

    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Autowired
    public DietPlanService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    public DietPlan saveDietPlan(addDietPlanRequest dietPlan){
        DietPlan d=new DietPlan();

        PhysicalCondition physicalCondition=physicalConditionRepository.findById(dietPlan.getConditionId()).orElse(null);
        if(physicalCondition!=null){
            d.setCondition(physicalCondition);
            d.setRecepies(dietPlan.getRecepies());
            d.setDescription(dietPlan.getDescription());
            return dietPlanRepository.save(d);
        }
        else return null;

    }

    public void notifyTraining(String dn){
        rabbitTemplate.convertAndSend(exchange,routingKey,dn);
    }

    public void notifyUser(String email){rabbitTemplate.convertAndSend(exchange,routingKey,email);}

    public void notifyTrainingPositive(User user){
        rabbitTemplate.convertAndSend(exchange,routingKey, user);
    }

    public List<DietPlan> saveDietPlans(List<DietPlan> dietPlanList){
        return dietPlanRepository.saveAll(dietPlanList);
    }

    public List<DietPlan> getDietPlans(){
        return dietPlanRepository.findAll();
    }

    public DietPlan getDietPlanById(int id){

        DietPlan d=dietPlanRepository.findById(id);
        if(d==null){
            throw new NotFoundException("DietPlan with id = " + id + " does not exist.");
        }
        else return d;
    }

    public DietPlan updateDietPlan(int id,updateDietPlanRequest updateDietPlan) {

        DietPlan d = dietPlanRepository.findById(id);
        if (d != null) {

            PhysicalCondition p = physicalConditionRepository.findById(updateDietPlan.getConditionId()).orElse(null);
            if (p != null) {
                d.setDescription(updateDietPlan.description);
                d.setCondition(p);
                d.setRecepies(updateDietPlan.getRecepies());
            }
            return dietPlanRepository.save(d);

        }
        else throw new NotFoundException("DietPlan with id = " + d.getId() + " does not exist.");
    }

    public String deleteDietPlan(int id){
        DietPlan d=dietPlanRepository.findById(id);
        if(d==null){
            return null;
        }
        else {
            dietPlanRepository.deleteById(id);
            return "Diet plan removed";
        }
    }

    public String addValidRecepies(int id) {
        User user = userRepository.findById(id).orElse(null);
        PhysicalCondition physicalCondition = physicalConditionRepository.findPhysicalConditionByUser(user);
        double bmi = physicalCondition.getBmi();
        DietPlan dietPlan = dietPlanRepository.findDietPlanByCondition_Id(physicalCondition.getId());

        if (bmi < 20) {
            int dedicated = 2;
            List<Recepie> recepie = recepieRepository.getAllByDedicated(dedicated);
            for (Recepie r : recepie) {
                DietPlan dietPlan1=new DietPlan(r.getSubject(),r.getRecepieText(),r.getImagePath(),physicalCondition,false);
                dietPlanRepository.save(dietPlan1);
            }
        } else if (bmi > 20 && bmi < 25) {
            int dedicated = 1;
            List<Recepie> recepie = recepieRepository.getAllByDedicated(dedicated);
            for (Recepie r : recepie) {
                DietPlan dietPlan1 = new DietPlan(r.getSubject(), r.getRecepieText(), r.getImagePath(),physicalCondition, false);
                dietPlanRepository.save(dietPlan1);
            }

        } else {
            int dedicated = 3;
            List<Recepie> recepie = recepieRepository.getAllByDedicated(dedicated);
            for (Recepie r : recepie) {
                DietPlan dietPlan1 = new DietPlan(r.getSubject(), r.getRecepieText(), r.getImagePath(),physicalCondition, false);
                dietPlanRepository.save(dietPlan1);
            }
        }
        return "Added valid recepies";

    }

    public List<DietPlan> getRecepiesForUser(String mail){
        User user=userRepository.findByEmail(mail).orElse(null);
        PhysicalCondition physicalCondition=physicalConditionRepository.findPhysicalConditionByUser_Id(user.getId());
        List<DietPlan> vracam=new ArrayList<>();
        List<DietPlan> svi=dietPlanRepository.findAll();
        for (DietPlan d:svi) {
            if(d.getCondition().equals(physicalCondition)){
                vracam.add(d);
            }
        }
        return vracam;

    }



}
