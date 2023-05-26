package com.GoFit.Schedule.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy="schedule")
    Set<Workout> workouts = new HashSet<>();
//    @ManyToMany
//    @JoinTable(
//            name = "ScheduleWorkout",
//            joinColumns = @JoinColumn(name = "scheduleId"),
//            inverseJoinColumns = @JoinColumn(name = "workoutId"))
//    @JsonIgnore
//    //@NotEmpty(message = "Workout must be provided.")
//    Set<Workout> workouts = new HashSet<>();

    private String title;

    public Schedule() {
    }

    public Schedule(User user, String dateRange1, String dateRange2) {
        this.user = user;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Workout> getWorkouts() {
//        return workouts;
//    }
//
//    public void setWorkouts(Set<Workout> workouts) {
//        this.workouts = workouts;
//    }
//
//    public void addWorkout(Workout workout) {
//        this.workouts.add(workout);
//    }

}
