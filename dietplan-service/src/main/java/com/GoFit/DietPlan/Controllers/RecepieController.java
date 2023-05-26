package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Models.Recepie;
import com.GoFit.DietPlan.Requests.*;
import com.GoFit.DietPlan.Repositories.RecepieRepository;
import com.GoFit.DietPlan.Requests.addNewRecepieRequest;
import com.GoFit.DietPlan.Services.RecepieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/dietplan")
public class RecepieController {

    @Autowired
    private RecepieService recepieService;

    @Autowired
    private RecepieRepository recepieRepository;

    @Autowired
    private RecepieController(RecepieService recepieService){this.recepieService=recepieService;}

    @PostMapping("/addNewRecepie")
    public ResponseEntity<String> addNewRecepie(@RequestBody addNewRecepieRequest addNewRecepie){
        return new ResponseEntity<>(recepieService.addNewRecepie(addNewRecepie), HttpStatus.OK);
    }

    @GetMapping("/recepies")
    public ResponseEntity<List<Recepie>> getAllRecepies(){
        return new ResponseEntity<List<Recepie>>(recepieService.getAllRecepies(),HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countRecepies(){
        return new ResponseEntity<Long>(recepieRepository.count(),HttpStatus.OK);
    }

    @PostMapping("/deleteRecepie")
    public ResponseEntity<String> deleteRecepie(@RequestBody deleteRecepieRequest deleteRecepieRequest){
        return new ResponseEntity<>(recepieService.deleteRecepie(deleteRecepieRequest.getId()),HttpStatus.OK);
    }

    @DeleteMapping("/deleteRecepie/{id}")
    public ResponseEntity<String> deleteRec(@PathVariable int id){
        return new ResponseEntity<>(recepieService.deleteRecepie(id),HttpStatus.OK);
    }


    @PutMapping("/editRecepie/{id}")
    public ResponseEntity<String> editRecepie(@PathVariable int id,@RequestBody editRecepieRequest editRecepie){
        return new ResponseEntity<>(recepieService.editRecepie(id,editRecepie),HttpStatus.OK);
    }
}
