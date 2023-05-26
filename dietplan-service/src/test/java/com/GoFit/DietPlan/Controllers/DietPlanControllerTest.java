package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Services.AthleteService;
import com.GoFit.DietPlan.Services.PhysicalConditionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DietPlanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private UserRepository athleteRepository;

    @Autowired
    private PhysicalConditionRepository physicalConditionRepository;

    @Autowired
    private PhysicalConditionService physicalConditionService;

    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Test
    public void TestAddDP() throws Exception {
        athleteRepository.deleteAll();
        physicalConditionRepository.deleteAll();
        dietPlanRepository.deleteAll();

        User user=new User("ime1","ime1@ime.com");
        user=athleteRepository.save(user);
        PhysicalCondition physicalCondition=new PhysicalCondition(user,"M","normal",185.0,85.0,1.0);
        physicalCondition=physicalConditionRepository.save(physicalCondition);

        int id=physicalCondition.getId();

        RequestBuilder requestBuilder= post("/dietplan/dietPlan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "conditionId":"%s",
        "description":"opis",
        "recepies":"recept"
        }""",id));


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void TestAddDPNull() throws Exception {
        RequestBuilder requestBuilder= post("/dietplan/dietPlan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "conditionId":"%s",
        "description":"opis",
        "recepies":"recept"
        }""",null));


       var res= mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    public void TestDeleteDP() throws Exception {
        RequestBuilder requestBuilder= delete("/dietplan/DeletedietPlan/"+125455);

        var res= mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    public void TestUpdateDP() throws Exception {
        athleteRepository.deleteAll();
        physicalConditionRepository.deleteAll();
        dietPlanRepository.deleteAll();

        User user=new User("ime1","ime1@ime.com");
        user=athleteRepository.save(user);

        PhysicalCondition physicalCondition=new PhysicalCondition(user,"M","normal",185.0,85.0,1.0);
        physicalCondition=physicalConditionRepository.save(physicalCondition);

        DietPlan dietPlan=new DietPlan("opis","recepti",physicalCondition,false);
        dietPlan=dietPlanRepository.save(dietPlan);

        RequestBuilder requestBuilder= put("/dietplan/updateDietPlan/"+dietPlan.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "conditionId":"%s",
        "description":"opis",
        "recepies":"receptt"
        }""",physicalCondition.getId()));
        ;

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("recepies",is("receptt")));

    }



}
