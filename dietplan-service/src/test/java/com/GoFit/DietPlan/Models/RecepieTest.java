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
public class RecepieTest {
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
    public void addSubjectNull(){
        Recepie recepie=new Recepie();
        recepie.setRecepieText("tekst");
        recepie.setDedicated(0);
        recepie.setSubject(null);
        List<ConstraintViolation<Recepie>> recepies=new ArrayList<>(validator.validate(recepie));

        assertEquals("Subject must be entered.",recepies.get(0).getMessage());
    }

    @Test
    public void addDescriptionNull(){
        Recepie recepie=new Recepie();
        recepie.setSubject("tekst");
        recepie.setRecepieText(null);
        recepie.setDedicated(0);
        List<ConstraintViolation<Recepie>> recepies=new ArrayList<>(validator.validate(recepie));

        assertEquals("Recipe must be entered.",recepies.get(0).getMessage());
    }

    @Test
    public void addDedicatedNull(){
        Recepie recepie=new Recepie();
        recepie.setRecepieText("tekst");
        recepie.setSubject("tekst");
        recepie.setDedicated(null);
        List<ConstraintViolation<Recepie>> recepies=new ArrayList<>(validator.validate(recepie));

        assertEquals("Group must be entered.",recepies.get(0).getMessage());
    }

    @Test
    public void addSubjectMax(){
        Recepie recepie=new Recepie();
        recepie.setRecepieText("tekst");
        recepie.setDedicated(0);
        String test="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis ultricies diam vitae sodales. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Duis eu porta sapien, non dignissim diam. Sed porta diam in bibendum faucibus. Nam et pretium tellus. Nullam urna quam, maximus sed lorem at, maximus eleifend nibh. Cras eu velit at orci ornare consectetur. Pellentesque ac ipsum aliquet, varius sapien eu, eleifend dui. Nam lobortis ornare quam, sed cursus quam lobortis sed."  +
                "Nullam convallis leo quis felis malesuada pretium. Nullam ac varius est. Phasellus nec arcu urna. Aenean ac placerat nulla. Cras luctus.";
        recepie.setSubject(test);
        List<ConstraintViolation<Recepie>> recepies=new ArrayList<>(validator.validate(recepie));

        assertEquals("Length must be at least 2 chars long.",recepies.get(0).getMessage());
    }

    @Test
    public void addDescriptionMax(){
        Recepie recepie=new Recepie();
        recepie.setSubject("tekst");
        recepie.setDedicated(0);
        String test="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis ultricies diam vitae sodales. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Duis eu porta sapien, non dignissim diam. Sed porta diam in bibendum faucibus. Nam et pretium tellus. Nullam urna quam, maximus sed lorem at, maximus eleifend nibh. Cras eu velit at orci ornare consectetur. Pellentesque ac ipsum aliquet, varius sapien eu, eleifend dui. Nam lobortis ornare quam, sed cursus quam lobortis sed."  +
                "Nullam convallis leo quis felis malesuada pretium. Nullam ac varius est. Phasellus nec arcu urna. Aenean ac placerat nulla. Cras luctus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis ultricies diam vitae sodales. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Duis eu porta sapien, non dignissim diam. Sed porta diam in bibendum faucibus. Nam et pretium tellus. Nullam urna quam, maximus sed lorem at, maximus eleifend nibh. Cras eu velit at orci ornare consectetur. Pellentesque ac ipsum aliquet, varius sapien eu, eleifend dui. Nam lobortis ornare quam, sed cursus quam lobortis sed." +
                "Nullam convallis leo quis felis malesuada pretium. Nullam ac varius est. Phasellus nec arcu urna. Aenean ac placerat nulla. Cras luctus.";
        recepie.setRecepieText(test);
        List<ConstraintViolation<Recepie>> recepies=new ArrayList<>(validator.validate(recepie));

        assertEquals("Length must be at least 2 chars long.",recepies.get(0).getMessage());
    }


}
