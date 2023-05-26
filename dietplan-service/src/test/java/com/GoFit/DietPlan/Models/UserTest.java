package com.GoFit.DietPlan.Models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ActiveProfiles("test")
public class UserTest {
    private static Validator validator;
    private static ValidatorFactory validatorFactory;
    @BeforeAll
    public static void createValidator() {
       validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }
    @Test
    public void testDisplayUserNameNull(){
        String name = null;
        User user =new User(

                "teste223",

                "ime@ime.com"
                );

        user.setName(name);
        List<ConstraintViolation<User>> users=new ArrayList<>(validator.validate(user));
       
        assertEquals("Name must be entered.",users.get(0).getMessage());

    }

    @Test
    public void testUserNameNull(){
        String name = null;
        User user =new User(

                "name",
                "ime@ime.com"
                );

        user.setName(name);
        List<ConstraintViolation<User>> users=new ArrayList<>(validator.validate(user));

        assertEquals("Name must be entered.",users.get(0).getMessage());

    }


    @Test
    public void testUserEmailNull(){
        String email = null;
        User user =new User(

                "ime",
                "email@email.com"
                );

        user.setEmail(email);
        List<ConstraintViolation<User>> users=new ArrayList<>(validator.validate(user));

        assertEquals("Email must be entered.",users.get(0).getMessage());
    }










}
