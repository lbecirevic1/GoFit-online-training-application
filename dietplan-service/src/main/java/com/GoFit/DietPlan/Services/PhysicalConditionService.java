package com.GoFit.DietPlan.Services;

import com.GoFit.DietPlan.Exceptions.NotFoundException;
import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Requests.UpdatePhysicalCondition;

import com.GoFit.DietPlan.Requests.addPhysicalConditionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysicalConditionService {
    @Autowired
    private PhysicalConditionRepository physicalConditionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DietPlanService dietPlanService;

    @Autowired
    private DietPlanRepository dietPlanRepository;

    public PhysicalCondition savePhysicalCondition(addPhysicalConditionRequest addPhysicalConditionRequest) {
        System.out.println("addPhysicalConditionRequest" + addPhysicalConditionRequest.bodyType);
        User u=userRepository.findByEmail(addPhysicalConditionRequest.mail).orElse(null);
        if(u != null) {
            PhysicalCondition ph1=physicalConditionRepository.findPhysicalConditionByUser_Id(u.getId());
            if(ph1!=null){
                for (DietPlan d:dietPlanRepository.findAll()){
                    if(d.getCondition()==ph1){
                        dietPlanRepository.delete(d);
                    }
                }
                physicalConditionRepository.delete(ph1);
            }
            PhysicalCondition p=new PhysicalCondition();
            System.out.println(u.getName());
            p.setUser(u);
            p.setPhysicalActivity(addPhysicalConditionRequest.physicalActivity);
            double bmi= addPhysicalConditionRequest.getWeight()/((addPhysicalConditionRequest.getHeight()/100)*(addPhysicalConditionRequest.getHeight()/100));
            p.setBmi(bmi);
            p.setGender(addPhysicalConditionRequest.gender);
            p.setHeight(addPhysicalConditionRequest.height);
            p.setWeight(addPhysicalConditionRequest.weight);
            p.setBodyType(addPhysicalConditionRequest.bodyType);
            PhysicalCondition physicalCondition=physicalConditionRepository.save(p);
             dietPlanService.addValidRecepies(u.getId());
            return physicalConditionRepository.save(p);
        }
        System.out.println("TESTTTT");
        return null;
    }

    public List<PhysicalCondition> savePhysicalConditions(List<PhysicalCondition> physicalConditionList){
        return physicalConditionRepository.saveAll(physicalConditionList);
    }

    public List<PhysicalCondition> getPhysicalConditions(){
        return physicalConditionRepository.findAll();
    }

    public PhysicalCondition getPhyscialConditionById(int id){
        PhysicalCondition existingPhysicalConcition=physicalConditionRepository.findById(id).orElse(null);
        if(existingPhysicalConcition==null){
            throw new NotFoundException("PhysicalCondition with id = " + id + " does not exist.");
        }
        return existingPhysicalConcition;
    }

    public PhysicalCondition updatePhysicalCondition(int id,UpdatePhysicalCondition physicalCondition){
        PhysicalCondition existingPhysicalConcition=physicalConditionRepository.findById(id).orElse(null);
        if(existingPhysicalConcition==null){
            throw new NotFoundException("PhysicalCondition with id = " + id + " does not exist.");
        }
        existingPhysicalConcition.setPhysicalActivity(physicalCondition.getPhysicalActivity());
        existingPhysicalConcition.setBodyType(physicalCondition.getBodyType());
        existingPhysicalConcition.setGender(physicalCondition.getGender());
        existingPhysicalConcition.setHeight(physicalCondition.getHeight());
        existingPhysicalConcition.setWeight(physicalCondition.getWeight());
      //  existingPhysicalConcition.setUser(userRepository.getById(physicalCondition.getIdUser()));
        return physicalConditionRepository.save(existingPhysicalConcition);
    }

    public String deletePhysicalCondition(int id){
        PhysicalCondition existingPhysicalCondition=physicalConditionRepository.findById(id).orElse(null);
        if(existingPhysicalCondition==null){
           return "PC not found";
        }
        else
        physicalConditionRepository.deleteById(id);
        return "Physical condition removed";
    }


    public String addBMIForUser(int id){
        User user=userRepository.findById(id).orElse(null);
        if(user==null){
            return "No user found";
        }
        PhysicalCondition physicalCondition=physicalConditionRepository.findPhysicalConditionByUser_Id(id);
        double bmi= physicalCondition.getWeight()/((physicalCondition.getHeight()/100)*(physicalCondition.getHeight())/100);
        physicalCondition.setBmi(bmi);
        physicalConditionRepository.save(physicalCondition);
        return "BMI updated";
    }


}
