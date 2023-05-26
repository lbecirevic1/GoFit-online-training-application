package com.GoFit.DietPlan.Models;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class DietPlanTests {
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
    public void testDescirptionNull(){
        DietPlan dietPlan=new DietPlan();
        dietPlan.setDescription(null);
        dietPlan.setRecepies("recept");
        List<ConstraintViolation<DietPlan>> validation=new ArrayList<>(validator.validate(dietPlan));

        assertEquals("Description must be entered.",validation.get(0).getMessage());
    }

    @Test
    public void testRecepiesNull(){
        DietPlan dietPlan=new DietPlan();
        dietPlan.setDescription("opis");
        String recept=null;
        dietPlan.setRecepies(recept);
        List<ConstraintViolation<DietPlan>> validation=new ArrayList<>(validator.validate(dietPlan));

        assertEquals("Recepies must be entered.",validation.get(0).getMessage());
    }

    @Test
    public void testRecepiesOk(){
        DietPlan dietPlan=new DietPlan();
        dietPlan.setDescription("opis");
        String recept="recept";
        dietPlan.setRecepies(recept);
        assertEquals("recept",dietPlan.getRecepies());

    }

    @Test
    public void testDescriptionOk(){
        DietPlan dietPlan=new DietPlan();
        dietPlan.setDescription("opis");
        String recept="recept";
        dietPlan.setRecepies(recept);
        assertEquals("opis",dietPlan.getDescription());
    }

    @Test
    public void testDescriptionMinimum(){
        DietPlan dietPlan=new DietPlan();
        dietPlan.setDescription("i");
        dietPlan.setRecepies("recept");
        List<ConstraintViolation<DietPlan>> validation=new ArrayList<>(validator.validate(dietPlan));
        assertEquals("Length must be at least 2 chars long.",validation.get(0).getMessage());
    }

    @Test
    public void testRecepiesMinimum(){
        DietPlan dietPlan=new DietPlan();
        dietPlan.setDescription("opis");
        dietPlan.setRecepies("i");
        List<ConstraintViolation<DietPlan>> validation=new ArrayList<>(validator.validate(dietPlan));

        assertEquals("Length must be at least 2 chars long.",validation.get(0).getMessage());
    }

}
