package com.GoFit.DietPlan.Responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String poruka;
    private Integer statusniKod;

    public Response(String poruka) {
        this(poruka, 200);
    }
}
