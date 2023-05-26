package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Feign.TrainingConsumer;
import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Requests.addDietPlanRequest;
import com.GoFit.DietPlan.Requests.updateDietPlanRequest;
import com.GoFit.DietPlan.Services.DietPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dietplan")
public class DietPlanController {

    @Autowired
    private DietPlanService dietPlanService;

    @Autowired
    private TrainingConsumer trainingConsumer;

    @Autowired
    private PhysicalConditionRepository physicalConditionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DietPlanController(DietPlanService dietPlanService){
        this.dietPlanService=dietPlanService;
    }

    @PostMapping("/dietPlan")
    public ResponseEntity<DietPlan> addDietPlan(@Valid @RequestBody addDietPlanRequest addDietPlanRequest) {
        if(dietPlanService.saveDietPlan(addDietPlanRequest)!=null){
            return new ResponseEntity("added",HttpStatus.CREATED);
        }
        else
            return new ResponseEntity("not added",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/dietPlans")
    public List<DietPlan> addDietPlans(@Valid @RequestBody List<DietPlan> dietPlans) {
        return dietPlanService.saveDietPlans(dietPlans);
    }

    @PostMapping("/responseTraining")
    public void responseTraining(@Valid @RequestBody String dn){
            dietPlanService.notifyTraining(dn);
    }

    @PostMapping("/responseTrainingPositive")
    public void responseTrainingPositive(@Valid @RequestBody User user){
        dietPlanService.notifyTrainingPositive(user);
    }

    @GetMapping("/responseUser")
    public void responseUser(){
        dietPlanService.notifyUser("ode poruka1");
    }


    @GetMapping("/dietPlans")
    public List<DietPlan> getDietPlans() {
        return dietPlanService.getDietPlans();
    }

    @GetMapping("/dietPlan/{id}")
    public DietPlan getDietPlanById(@PathVariable int id) {
        return dietPlanService.getDietPlanById(id);
    }

    @GetMapping("/dietPlan/calories/{id}/{date}")
    public Double getCalories(@PathVariable int id, @PathVariable String date){
       return trainingConsumer.getCaloriesByDate(id,date);
    }

    @PutMapping("/updateDietPlan/{id}")
    public DietPlan updateDietPlan(@PathVariable int id,@Valid @RequestBody updateDietPlanRequest updateDietPlan) {
        return dietPlanService.updateDietPlan(id,updateDietPlan);
    }

    @DeleteMapping("/DeletedietPlan/{id}")
    public ResponseEntity<DietPlan> deleteDietPlan(@PathVariable int id) {
        if(dietPlanService.deleteDietPlan(id)==null){
            return new ResponseEntity(dietPlanService.deleteDietPlan(id),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dietPlanService.deleteDietPlan(id),HttpStatus.OK);
    }

    @PostMapping("/addValidRecepiesForUser/{id}")
    public ResponseEntity addValidRecepiesForUser(@PathVariable int id){
        return new ResponseEntity(dietPlanService.addValidRecepies(id), HttpStatus.OK);
    }

    @GetMapping("/getRecepiesForUser/{email}")
    public ResponseEntity getRecepiesForUser(@PathVariable String email){
        return new ResponseEntity(dietPlanService.getRecepiesForUser(email),HttpStatus.OK);
    }

    @GetMapping("/userHasPC/{email}")
    public ResponseEntity userHAsPC(@PathVariable String email){
        User user=userRepository.findByEmail(email).orElse(null);
        PhysicalCondition physicalCondition=physicalConditionRepository.findPhysicalConditionByUser(user);
        if(physicalCondition!=null){
            return new ResponseEntity(physicalCondition,HttpStatus.OK);
        }
        return new ResponseEntity("not found",HttpStatus.OK);
    }

}



