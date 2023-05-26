package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.GoFit.DietPlan.Services.AthleteService;
import com.GoFit.DietPlan.Services.PhysicalConditionService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhysicalConditionControllerTest {

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
    public void TestAddPC() throws Exception {
        User user=new User("ime1","ime1@ime.com");
        user=athleteRepository.save(user);
        int id=user.getId();

        RequestBuilder requestBuilder= post("/dietplan/physicalCondition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "id":"%s",
        "gender":"F",
        "bodyType":"normal",
        "height":185.0,
        "weight":85.0,
        "physicalActivity":1.0
        }""",id));


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetPCs() throws Exception {

        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/dietplan/physicalConditions");


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetPCById() throws Exception {
        int id=156452;
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/dietplan/physicalCondition/"+id);


        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());

    }

    @Test
    public void TestUpdatePC() throws Exception {
        physicalConditionRepository.deleteAll();
        User user=new User("ime1","ime1@ime.com");
        user=athleteRepository.save(user);
        int id=user.getId();

        RequestBuilder requestBuilder= post("/dietplan/physicalCondition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "id":"%s",
        "gender":"F",
        "bodyType":"normal",
        "height":185.0,
        "weight":85.0,
        "physicalActivity":1.0
        }""",id));


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
        int id2=physicalConditionRepository.findAll().get(0).getId();

        RequestBuilder requestBuilder2= MockMvcRequestBuilders.put("/dietplan/physicalCondition/"+id2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "id":"%s",
        "gender":"F",
        "bodyType":"fat",
        "height":185.0,
        "weight":85.0,
        "physicalActivity":1.0
        }""",id));


        mockMvc.perform(requestBuilder2)
                .andExpect(status().isOk());

        RequestBuilder requestBuilder3= MockMvcRequestBuilders.get("/dietplan/physicalConditions");
        var result=mockMvc.perform(requestBuilder3)
                .andExpect(status().isOk())
                .andReturn();
        result.getResponse().getContentType().equals(String.format("""
        {
        "id":"%s",
        "gender":"F",
        "bodyType":"fat",
        "height":185.0,
        "weight":85.0,
        "physicalActivity":1.0
        }""",id));

    }

    @Test
    public void TestDeletePC() throws Exception {
        physicalConditionRepository.deleteAll();
        User user=new User("ime1","ime1@ime.com");
        user=athleteRepository.save(user);
        int id=user.getId();

        RequestBuilder requestBuilder= post("/dietplan/physicalCondition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
        {
        "id":"%s",
        "gender":"F",
        "bodyType":"normal",
        "height":185.0,
        "weight":85.0,
        "physicalActivity":1.0
        }""",id));


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        dietPlanRepository.deleteAll();

        int id2=physicalConditionRepository.findAll().get(0).getId();

        RequestBuilder requestBuilder2= delete("/dietplan/physicalCondition/"+id2);
        mockMvc.perform(delete(String.format("/dietplan/physicalCondition/%s",id2)))
                .andExpect(status().isOk());


    }

    @Test
    public void TestPCNotFound() throws Exception {
        mockMvc.perform(delete(String.format("/dietplan/physicalCondition/%s", 125455))
                .content("{}"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void TestSetBMI() throws Exception {
        mockMvc.perform(post(String.format("/dietplan/setBMI/%s", 125455))
                        .content("{}"))
                .andExpect(status().isOk());
    }








}
