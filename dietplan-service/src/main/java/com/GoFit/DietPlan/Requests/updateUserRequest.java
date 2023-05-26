package com.GoFit.DietPlan.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateUserRequest {
    public int userId;
    public String name;
    public String lastName;
    public String password;
    public String displayName;
    public String email;
    public Date birthday;
}

