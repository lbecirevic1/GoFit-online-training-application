package com.GoFit.SystemEvent.DietPlan.Controller;

import com.GoFit.SystemEvent.DietPlan.Model.Event;
import com.GoFit.SystemEvent.DietPlan.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }
}
