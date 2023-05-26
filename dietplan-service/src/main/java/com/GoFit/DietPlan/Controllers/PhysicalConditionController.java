package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Requests.UpdatePhysicalCondition;
import com.GoFit.DietPlan.Requests.addPhysicalConditionRequest;
import com.GoFit.DietPlan.Services.AthleteService;
import com.GoFit.DietPlan.Services.PhysicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dietplan")
public class PhysicalConditionController {

    @Autowired
    private PhysicalConditionService physicalConditionService;
    private AthleteService athleteService;

    @PostMapping("/physicalCondition")
    public ResponseEntity<PhysicalCondition> addPhysicalCondition(@Valid @RequestBody addPhysicalConditionRequest addPhysicalConditionRequest) {
        return new ResponseEntity<PhysicalCondition>(physicalConditionService.savePhysicalCondition(addPhysicalConditionRequest),HttpStatus.OK);
    }

    @PostMapping("/physicalConditions")
    public ResponseEntity<List<PhysicalCondition>> addPhysicalConditions(@Valid @RequestBody List<PhysicalCondition> physicalConditions) {
        return new ResponseEntity<List<PhysicalCondition>>(physicalConditionService.savePhysicalConditions(physicalConditions),HttpStatus.OK);
    }

    @GetMapping("/physicalConditions")
    public ResponseEntity<List<PhysicalCondition>> getPhysicalCondition() {
        return new ResponseEntity<List<PhysicalCondition>>(physicalConditionService.getPhysicalConditions(),HttpStatus.OK);
    }

    @GetMapping("/physicalCondition/{id}")
    public ResponseEntity<PhysicalCondition> getPhysicalConditionById(@PathVariable int id) {
        return new ResponseEntity<PhysicalCondition>(physicalConditionService.getPhyscialConditionById(id),HttpStatus.OK);
    }

    @PutMapping("/physicalCondition/{id}")
    public ResponseEntity<PhysicalCondition> updatePhysicalCondition(@PathVariable int id,@Valid @RequestBody UpdatePhysicalCondition physicalCondition) {
        return new ResponseEntity<PhysicalCondition>(physicalConditionService.updatePhysicalCondition(id,physicalCondition),HttpStatus.OK);
    }

    @DeleteMapping("/physicalCondition/{id}")
    public ResponseEntity<String> deletePhysicalCondition(@PathVariable int id) {
        return new ResponseEntity<String>(physicalConditionService.deletePhysicalCondition(id), HttpStatus.OK);

    }

    @PostMapping("/setBMI/{id}")
    public ResponseEntity<String> addBMIForUser(@PathVariable int id){
        return new ResponseEntity<String>(physicalConditionService.addBMIForUser(id),HttpStatus.OK);
    }


}
