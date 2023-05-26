package com.GoFit.DietPlan.Controllers;

import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Services.AthleteService;
import com.GoFit.DietPlan.Services.RecepieService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AthleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private UserRepository athleteRepository;

      @BeforeEach
    public void setUp(){
        athleteRepository.deleteAll();
    }

    @Test
    public void TestCreateUsers() throws Exception {
        String input1="{\n" +
                "  \"name\": \"ime1\",\n" +
                "  \"email\": \"ime@ime.com\"\n"+
                "}";
        String input2="{\n" +
                "  \"name\": \"ime2\",\n" +
                "  \"email\": \"ime2@ime.com\"\n"+
                "}";

        String input3="[\n"+
                "{\n" +
        "  \"name\": \"ime1\",\n" +
                "  \"email\": \"ime@ime.com\"\n"+
                "},"+

                "{\n" +
                "  \"name\": \"ime2\",\n" +
                "  \"email\": \"ime2@ime.com\"\n"+
                "}\n"+
                "]";

        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/dietplan/user/createUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input3);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetUser() throws Exception {
        athleteRepository.deleteAll();
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/dietplan/user/users")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetUnknownUser() throws Exception {
        athleteRepository.deleteAll();
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/dietplan/user/12578")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    public void TestCreateUser() throws Exception {
        String input1="{\n" +
                "  \"name\": \"ime1\",\n" +
                "  \"email\": \"ime@ime.com\"\n"+
                "}";
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/dietplan/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input1);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

    }

   @Test
    public void TestGetUserById() throws Exception {
        int id=1245;
       RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/dietplan/user/"+id);

       mockMvc.perform(requestBuilder)
               .andExpect(status().isNotFound());


   }






}
