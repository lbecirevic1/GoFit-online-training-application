//package com.GoFit.Training.Controllers;
//
//import com.GoFit.Training.TrainingApplication;
//import org.json.JSONArray;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(
//        classes = TrainingApplication.class,
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//@AutoConfigureMockMvc
////@Transactional
//@TestPropertySource(
//        locations = "classpath:application-test.properties"
//)
//class AthleteControllerTest {
//
//    @Autowired
//    public MockMvc mockMvc;
//
////    @Rollback(value = true)
//    @Test
//    @Transactional
//    @Order(1)
//    void createAthlete() throws Exception {
//        mockMvc.perform(post("/athlete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"Lejla\", \"displayName\" : \"lejlalab\"}"))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @Order(2)
//    void getAthlete() throws Exception {
//        mockMvc.perform(post("/athlete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"Lejla\", \"displayName\" : \"lejlalab\"}"));
//
//        mockMvc.perform(get("/athlete/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().json(("{\"id\": 1, \"name\": Lejla, \"displayName\" : lejlalab, \"trainingLevel\":null}"))).andReturn();
//    }
//
//
//    @Test
//    @Order(3)
//    void updateAthlete() throws Exception {
////        mockMvc.perform(post("/athlete")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{\"name\": \"Lejla\", \"displayName\" : \"lejlalab\"}"));
//
//        mockMvc.perform(put("/athlete/{id}", 2).content("{\"name\": \"lejlaNovo\", \"displayName\" : \"lejlalab\"}"))
//                .andExpect(status().isOk());
//    }
////
////    @Test
////    void deleteAthlete() {
////    }
////
////    @Test
////    void updateDisplayName() {
////    }
////
////    @Test
////    void updateTrainingLevel() {
////    }
////
////    @Test
////    void testUpdateAthlete() {
////    }
//}