package com.GoFit.DietPlan.Models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhysicalConditionTests {
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
    public void testGenderNull(){
        User user1=new User();
        PhysicalCondition condition=new PhysicalCondition(
                user1,
                "F",
                "normal",
                165.0,
                65.0,
                (65.0)/(165.0*165.0),
                4.0

        );
        condition.setGender(null);
        List<ConstraintViolation<PhysicalCondition>> validation=new ArrayList<>(validator.validate(condition));

        assertEquals("Gender must be entered.",validation.get(0).getMessage());

    }

    @Test
    public void testBodyTypeNull(){
        User user1=new User();
        PhysicalCondition condition=new PhysicalCondition(
                user1,
                "F",
                "normal",
                165.0,
                65.0,
                (65.0)/(165.0*165.0),
                4.0

        );
        condition.setBodyType(null);
        List<ConstraintViolation<PhysicalCondition>> validation=new ArrayList<>(validator.validate(condition));

        assertEquals("bodytype must be entered",validation.get(0).getMessage());
    }

    @Test
    public void testHeightNegative(){
        User user1=new User();
        PhysicalCondition condition=new PhysicalCondition(
                user1,
                "F",
                "normal",
                165.0,
                65.0,
                (65.0)/(165.0*165.0),
                4.0

        );
        condition.setHeight(Double.valueOf(-1));
        List<ConstraintViolation<PhysicalCondition>> validation=new ArrayList<>(validator.validate(condition));

        assertEquals("Height must be positive!",validation.get(0).getMessage());
    }

    @Test
    public void testWeightNegative(){
        User user1=new User();
        PhysicalCondition condition=new PhysicalCondition(
                user1,
                "F",
                "normal",
                165.0,
                65.0,
                (65.0)/(165.0*165.0),
                4.0

        );
        condition.setWeight(Double.valueOf(-1));
        List<ConstraintViolation<PhysicalCondition>> validation=new ArrayList<>(validator.validate(condition));

        assertEquals("Weight must be positive!",validation.get(0).getMessage());
    }

    @Test
    public void testBMINegative(){
        User user1=new User();
        PhysicalCondition condition=new PhysicalCondition(
                user1,
                "F",
                "normal",
                165.0,
                65.0,
                (65.0)/(165.0*165.0),
                4.0

        );
        condition.setBmi(Double.valueOf(-1));
        List<ConstraintViolation<PhysicalCondition>> validation=new ArrayList<>(validator.validate(condition));

        assertEquals("BMI must be positive!",validation.get(0).getMessage());
    }

    @Test
    public void testPhysicalActivityNegative(){
        User user1=new User();
        PhysicalCondition condition=new PhysicalCondition(
                user1,
                "F",
                "normal",
                165.0,
                65.0,
                (65.0)/(165.0*165.0),
                4.0

        );
        condition.setPhysicalActivity(Double.valueOf(-1));
        List<ConstraintViolation<PhysicalCondition>> validation=new ArrayList<>(validator.validate(condition));

        assertEquals("Physical activity must be positive!",validation.get(0).getMessage());
    }
}
