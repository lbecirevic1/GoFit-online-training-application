package com.GoFit.DietPlan.Responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalConditionResponses {
    private String gender;
    private String bodyType;
    private Double height;
    private Double weight;
    private Double bmi;
    private Double physicalActivity;
}

