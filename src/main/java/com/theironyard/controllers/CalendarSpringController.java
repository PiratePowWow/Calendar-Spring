package com.theironyard.controllers;

import com.theironyard.entities.Event;
import com.theironyard.services.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

/**
 * Created by PiratePowWow on 3/14/16.
 */
@Controller
public class CalendarSpringController {
    @Autowired
    EventRepository events;
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("events", events.findAllByOrderByDateTimeDesc());
        model.addAttribute("now", LocalDateTime.now());
        return "home";
    }
    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(String description, String dateTime){
        Event event = new Event(description,LocalDateTime.parse(dateTime));
        events.save(event);
        return "redirect:/";
    }

}
