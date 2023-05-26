package com.GoFit.DietPlan.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhysicalCondition {
    public int idUser;
    public String gender;
    public String bodyType;
    public Double height;
    public Double weight;
    public Double physicalActivity;

}


