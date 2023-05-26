//package com.GoFit.Training.Models;
//
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
//public class ExerciseTest {
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
//    public void exerciseNameEmptyTest(){
//        Exercise exercise = new Exercise();
//        exercise.setDescription("Lunges are a popular strength training exercise.");
//        exercise.setImage("Image link");
//        exercise.setVideoLink("");
//        exercise.setDuration(30.0);
//        exercise.setName("");
//        List<ConstraintViolation<Exercise>> validation = new ArrayList<>(validator.validate(exercise));
//
//        assertEquals("Name must be provided.",validation.get(0).getMessage());
//    }
//
//    @Test
//    public void exerciseDescriptionNullTest(){
//        Exercise exercise = new Exercise();
//        exercise.setDescription(null);
//        exercise.setImage("Image link");
//        exercise.setVideoLink("");
//        exercise.setDuration(30.0);
//        exercise.setName("Lunges");
//        List<ConstraintViolation<Exercise>> validation = new ArrayList<>(validator.validate(exercise));
//
//        assertEquals("Exercise description must be provided.",validation.get(0).getMessage());
//    }
//
//    @Test
//    public void exerciseImageNullTest(){
//        Exercise exercise = new Exercise();
//        exercise.setDescription("Description");
//        exercise.setImage(null);
//        exercise.setVideoLink("");
//        exercise.setDuration(30.0);
//        exercise.setName("Lunges");
//        List<ConstraintViolation<Exercise>> validation = new ArrayList<>(validator.validate(exercise));
//
//        assertEquals("Image link for an exercise must be provided.",validation.get(0).getMessage());
//    }
//
//    @Test
//    public void exerciseImageEmptyTest(){
//        Exercise exercise = new Exercise();
//        exercise.setDescription("Description");
//        exercise.setImage("");
//        exercise.setVideoLink("");
//        exercise.setDuration(30.0);
//        exercise.setName("Lunges");
//        List<ConstraintViolation<Exercise>> validation = new ArrayList<>(validator.validate(exercise));
//
//        assertEquals("Image link for an exercise must be provided.",validation.get(0).getMessage());
//    }
//
//    @Test
//    public void exerciseDescriptionLengthTest(){
//        Exercise exercise = new Exercise();
//        exercise.setDescription("Des");
//        exercise.setImage("Image");
//        exercise.setVideoLink("");
//        exercise.setDuration(30.0);
//        exercise.setName("Lunges");
//        List<ConstraintViolation<Exercise>> validation = new ArrayList<>(validator.validate(exercise));
//
//        assertEquals("Please provide min 10 and max 1000 characters.",validation.get(0).getMessage());
//    }
//
//}
