package com.GoFit.Schedule.Scheduling;

import com.GoFit.Schedule.Exceptions.NotFoundException;
import com.GoFit.Schedule.Models.Schedule;
import com.GoFit.Schedule.Models.User;
import com.GoFit.Schedule.Models.Workout;
import com.GoFit.Schedule.Services.AthleteService;
import com.GoFit.Schedule.Services.ScheduleService;
import com.GoFit.Schedule.Services.WorkoutService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@EnableScheduling
@Component
public class ScheduledTasks {
    @Autowired
    private AthleteService userService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron = "*/30 * * * * *")
    public void getAllUsers() throws ParseException {

        System.out.println("Start schedule task");
        DateTime thisMoment = new DateTime();
        List<Workout> workoutList = workoutService.getWorkouts();
        if(workoutList.size() == 0) {
            System.out.println("There is no created workout yet");
        }
        workoutList.forEach((workout) -> {
            System.out.println("Cheking if workout with id " + workout.getId() + " start in half hours");
            String pattern = "yyyy-MM-dd'T'HH:mm";
            DateTimeFormatter dtf = DateTimeFormat
                    .forPattern(pattern);
            DateTime startDate = dtf.parseDateTime(workout.getStart());
            DateTime endDate = dtf.parseDateTime(workout.getEnd());



            if(thisMoment.isAfter(startDate) && thisMoment.isBefore(endDate)) {
                System.out.println("Workout with id " + workout.getId() + " start in half hours. \n Send mail");
                Schedule schedule = scheduleService.getSchedule(workout.getSchedule().getId());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(schedule.getUser().getEmail());
                message.setText("Hello " + schedule.getUser().getName() +  ",\n" +
                        " \n" +
                        "You should stay fit! \n" +
                        "Your workout " + workout.getTitle()+ " start at " + workout.getStart() + ". \n"+
                        "Please checkout your calendar");
                message.setSubject("GoFit Reminder");
                message.setFrom("lejlamujic998@gmail.com");

                javaMailSender.send(message);



            }

        });




//        Calendar date = Calendar.getInstance();
//        System.out.println("Current Date and TIme : " + date.getTime());
//        long timeInSecs = date.getTimeInMillis();
//
//        Date afterAdding10Mins = new Date(timeInSecs + (30 * 60 * 1000));
//        System.out.println("After adding 10 mins : " + afterAdding10Mins);

//        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
//        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
//
//        String dateInString = "22-01-2015 10:15:55 AM";
//        Date dateq = formatter.parse(dateInString);
//        String formattedDateString = formatter.format(dateq);
        System.out.println("End schedule task");


    }
}
