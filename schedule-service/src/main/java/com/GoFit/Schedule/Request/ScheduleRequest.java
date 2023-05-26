package com.GoFit.Schedule.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    public Integer userId;
    public String email;
    public List<Integer> workouts;
}
