package com.example.eventsmicroservice.Controllers;

import com.example.eventsmicroservice.Entities.Event;
import com.example.eventsmicroservice.Entities.EventRelation;
import com.example.eventsmicroservice.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/month")
    public List<Event> getEventsForMonth(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date end) {
        return eventService.getEventsForMonth(start, end);
    }

    @GetMapping("/relations")
    public List<EventRelation> getEventRelations(@RequestParam("mainEventId") Long mainEventId) {
        return eventService.getEventRelations(mainEventId);
    }

    // Other controller methods as needed...
}
