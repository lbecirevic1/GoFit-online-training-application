package com.GoFit.Schedule.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutRequest {
    public String note;
    public String title;
    public String start;
    public String end;
    public String trainings;
    public int scheduleId;
}
