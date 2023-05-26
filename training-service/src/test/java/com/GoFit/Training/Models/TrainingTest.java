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
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ActiveProfiles("test")
//
//public class TrainingTest {
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
//    public void trainingTypeEmptyTest(){
//        Exercise exercise1 = new Exercise("Lunges1", 30.0, "Video link1", "Description1", "Image1");
//        Exercise exercise2 = new Exercise("Lunges2", 30.0, "Video link2", "Description2", "Image2");
//        Set exercises = new HashSet<>();
//        exercises.add(exercise1);
//        exercises.add(exercise2);
//
//        Training training = new Training();
//        training.setExercises(exercises);
//        training.setType("");
//        training.setName("Cardio");
//
//        List<ConstraintViolation<Training>> validation = new ArrayList<>(validator.validate(training));
//
//        assertEquals("Training type can not be empty.",validation.get(0).getMessage());
//    }
//
//    @Test
//    public void trainingNameNullTest(){
//        Exercise exercise1 = new Exercise("Lunges1", 30.0, "Video link1", "Description1", "Image1");
//        Exercise exercise2 = new Exercise("Lunges2", 30.0, "Video link2", "Description2", "Image2");
//        Set exercises = new HashSet<>();
//        exercises.add(exercise1);
//        exercises.add(exercise2);
//
//        Training training = new Training();
//        training.setExercises(exercises);
//        training.setType("Cardio");
//        training.setName(null);
//
//        List<ConstraintViolation<Training>> validation = new ArrayList<>(validator.validate(training));
//
//        assertEquals("Training name must be provided.",validation.get(0).getMessage());
//    }
//
//    @Test
//    public void trainingExercisesNullTest(){
//        Set<Exercise> exercises = new HashSet<>();
//
//        Training training = new Training();
//        training.setExercises(exercises);
//        training.setType("Cardio");
//        training.setName("Cardio");
//
//        List<ConstraintViolation<Training>> validation = new ArrayList<>(validator.validate(training));
//
//        assertEquals("Exercises must be provided.",validation.get(0).getMessage());
//    }
//}
