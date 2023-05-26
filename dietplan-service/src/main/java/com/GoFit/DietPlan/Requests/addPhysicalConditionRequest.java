package com.GoFit.DietPlan.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class addPhysicalConditionRequest {
    public String mail;
    public String gender;
    public String bodyType;
    public Double height;
    public Double weight;
    public Double physicalActivity;


}
