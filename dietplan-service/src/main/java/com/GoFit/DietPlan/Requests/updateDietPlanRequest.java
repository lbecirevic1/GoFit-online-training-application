package com.GoFit.DietPlan.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class updateDietPlanRequest {
    public int dietPlanId;
    public String description;
    public String recepies;
    public int conditionId;
}
