package com.GoFit.Schedule.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @ManyToMany(mappedBy = "workouts")
//    @JsonIgnore
//    Set<Schedule> schedules = new HashSet<>();
@ManyToOne
@JoinColumn(name="scheduleId", nullable=false)
private Schedule schedule;


    //@NotNull(message = "")
    private String note;

    @NotNull(message = "Workout must have a defined start")
    private String startt;

    @NotNull(message = "Workout must have a defined end")
    private String endd;


    @NotNull(message = "Workout must have defined training title")
    @NotEmpty(message = "Workout must have defined training title")
    private String title;

    private String trainings;


    public Workout() {
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Workout(String note, String start, String end, String type, String trainings) {
        //this.schedules = schedules;
        this.note = note;
        this.startt = start;
        this.endd = end;
        this.title = type;
        this.trainings=trainings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Set<Schedule> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }
//    public void addSchedule(Schedule schedule) {
//        this.schedules.add(schedule);
//    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStart() {
        return startt;
    }

    public void setStart(String dateTimeStart) {
        this.startt = dateTimeStart;
    }

    public String getEnd() {
        return endd;
    }

    public void setEnd(String dateTimeEnd) {
        this.endd = dateTimeEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String type) {
        this.title = type;
    }

    public String getTrainings() {
        return trainings;
    }

    public void setTrainings(String trainings) {
        this.trainings = trainings;
    }
}
