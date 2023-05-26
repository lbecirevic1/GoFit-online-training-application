//package com.GoFit.Training.Models;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.context.ActiveProfiles;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ActiveProfiles("test")
//public class TrainingHistoryTest {
//    private static Validator validator;
//    private static ValidatorFactory validatorFactory;
//
//    @BeforeAll
//    public static void createValidator() {
//        validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//
//    @AfterAll
//    public static void close() {
//        validatorFactory.close();
//    }
//
//    @Test
//    public void doneEmptyTest(){
//        TrainingHistory trainingHistory = new TrainingHistory();
//        trainingHistory.setDone("");
//        trainingHistory.setDate("31.03.2022.");
//        List<ConstraintViolation<TrainingHistory>> validation = new ArrayList<>(validator.validate(trainingHistory));
//
//        assertEquals("Mark your training as finished or unfinished.",validation.get(0).getMessage());
//    }
//}
