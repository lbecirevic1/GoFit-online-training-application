//package com.GoFit.Training.Models;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
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
//public class UserTest {
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
////    @Test
////    public void userWronDisplayNameTest(){
////        User athlete = new User();
////        athlete.set("Lejla-Lab");
////        athlete.setName("Lejla");
////
////        List<ConstraintViolation<User>> validation = new ArrayList<>(validator.validate(athlete));
////
////        assertEquals("Display name can only contain letters and digits and must contain 6 to 12 characters.",validation.get(0).getMessage());
////    }
////
////    @Test
////    public void userEmptyNameTest(){
////        User athlete = new User();
////        athlete.setDisplayName("Lejla-Lab");
////        athlete.setName("");
////
////        List<ConstraintViolation<User>> validation = new ArrayList<>(validator.validate(athlete));
////
////        assertEquals("Display name can only contain letters and digits and must contain 6 to 12 characters.",validation.get(0).getMessage());
////    }
////
////    @Test
////    public void userWrongNameTest(){
////        User athlete = new User();
////        athlete.setDisplayName("Lejla-Lab");
////        athlete.setName("L");
////
////        List<ConstraintViolation<User>> validation = new ArrayList<>(validator.validate(athlete));
////
////        assertEquals("Display name can only contain letters and digits and must contain 6 to 12 characters.",validation.get(0).getMessage());
////    }
//
//
//}
