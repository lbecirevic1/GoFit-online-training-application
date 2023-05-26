package com.GoFit.Schedule.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteRequest {
    public String name;
    public String lastname;
    public String displayName;
    public String email;
    public String birthday;
}
