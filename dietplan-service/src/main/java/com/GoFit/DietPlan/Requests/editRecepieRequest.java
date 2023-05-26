package com.GoFit.DietPlan.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class editRecepieRequest {
    private Integer id;
    private String recepieText;
}
