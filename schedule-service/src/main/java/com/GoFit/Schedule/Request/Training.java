package com.GoFit.Schedule.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    public Integer id;

    public Double duration;
    public String name;
    public String type;
    public List<Integer> exercises;
}
