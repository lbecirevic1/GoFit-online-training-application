package com.GoFit.DietPlan.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class PhysicalCondition {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    @NotBlank(message = "Gender must be entered.")
    @Size(min = 1, message = "Length must be M or F.")
    private String gender;

    @NotBlank(message = "bodytype must be entered")
    @Size(min = 2, message = "Length must be at least 2 chars long.")
    private String bodyType;

    @Min(value = 0,message = "Height must be positive!")
    private Double height;

    @Min(value = 0,message = "Weight must be positive!")
    private Double weight;

    @Min(value = 0,message = "BMI must be positive!")
    private Double bmi;

    @Min(value = 0,message = "Physical activity must be positive!")
    private Double physicalActivity;

    public PhysicalCondition(User user, String gender, String bodyType, Double height, Double weight, Double physicalActivity) {

        this.user = user;
        this.gender = gender;
        this.bodyType = bodyType;
        this.height = height;
        this.weight = weight;
        this.bmi = weight/((height/100)*(height/100));
        this.physicalActivity = physicalActivity;
    }

    public PhysicalCondition(User user, String gender, String bodyType, Double height, Double weight, Double bmi, Double physicalActivity) {
        this.user = user;
        this.gender = gender;
        this.bodyType = bodyType;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.physicalActivity = physicalActivity;
    }

    public PhysicalCondition() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(Double physicalActivity) {
        this.physicalActivity = physicalActivity;
    }
}
