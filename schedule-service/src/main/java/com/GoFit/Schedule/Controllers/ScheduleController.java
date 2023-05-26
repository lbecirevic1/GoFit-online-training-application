package com.GoFit.Schedule.Controllers;

import com.GoFit.Schedule.Models.Schedule;
import com.GoFit.Schedule.Request.ScheduleRequest;
import com.GoFit.Schedule.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@EnableScheduling
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable int id) {
        return new ResponseEntity<Schedule>(scheduleService.getSchedule(id), HttpStatus.OK);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<Schedule> getScheduleByUserId(@PathVariable String userId) {
        return new ResponseEntity<Schedule>(scheduleService.getScheduleByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getSchedules() {
        return new ResponseEntity<List<Schedule>>(scheduleService.getSchedules(), HttpStatus.OK);
    }

    // POST
    @PostMapping("/createSchedule")
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequest schedule) {
        Schedule createdSchedule = scheduleService.addSchedule(schedule);
        return new ResponseEntity<Schedule>(createdSchedule, HttpStatus.CREATED);
    }

    @PostMapping("/schedules")
    public ResponseEntity<List<Schedule>> createSchedules(@Valid @RequestBody List<Schedule> schedules) {
        return new ResponseEntity<List<Schedule>>(scheduleService.addSchedules(schedules), HttpStatus.CREATED);

    }

    // PUT
//    @PutMapping("/schedule/{id}")
//    public ResponseEntity<Schedule> updateSchedule(@PathVariable int id, @RequestBody Schedule schedule) {
//        return new ResponseEntity<Schedule>(scheduleService.putSchedule(id, schedule), HttpStatus.OK);
//    }

    // PATCH
    @PatchMapping("/schedule/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable int id, @Valid @RequestBody Map<Object, Object> objectMap) {
        return new ResponseEntity<Schedule>(scheduleService.updateSchedule(id, objectMap), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable int id) {
        return new ResponseEntity<String>(scheduleService.deleteSchedule(id), HttpStatus.OK);
    }
}
