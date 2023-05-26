package com.GoFit.DietPlan.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addNewRecepieRequest {
    private String subject;
    private String recepieText;
    private Integer dedicated;
}
