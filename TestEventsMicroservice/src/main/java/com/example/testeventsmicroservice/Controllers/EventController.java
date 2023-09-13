package com.example.testeventsmicroservice.Controllers;

import com.example.testeventsmicroservice.Entities.EventEntity;
import com.example.testeventsmicroservice.Entities.EventKafkaEntity;
import com.example.testeventsmicroservice.Entities.LinkedEventEntity;
import com.example.testeventsmicroservice.Services.EventService;
import com.example.testeventsmicroservice.Services.LinkedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {


    private final EventService eventService;

    private final LinkedEventService linkedEventService;

    @Autowired
    public EventController(EventService eventService, LinkedEventService linkedEventService) {
        this.eventService = eventService;
        this.linkedEventService = linkedEventService;
    }

    // Existing method for creating or updating an event
    @PostMapping("/createOrUpdateEvent")
    public ResponseEntity<EventEntity> createOrUpdateEvent(@RequestBody EventKafkaEntity eventKafkaEntity) {
        try {
            EventEntity savedEventEntity = eventService.createOrUpdateEvent(eventKafkaEntity);
            return new ResponseEntity<>(savedEventEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            // log the error (consider using a logger instead of printStackTrace)
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoints for fetching data from the database
    @GetMapping("/byTime")
    public ResponseEntity<List<EventEntity>> getEventsByTime(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return new ResponseEntity<>(eventService.getEventsByTime(time), HttpStatus.OK);
    }


    @GetMapping("/byTimeAndCountry")
    public ResponseEntity<List<EventEntity>> getEventsByTimeAndCountry(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date time,
            @RequestParam String country) {
        return new ResponseEntity<>(eventService.getEventsByTimeAndCountry(time, country), HttpStatus.OK);
    }


    @GetMapping("/byTimeAndType")
    public ResponseEntity<List<EventEntity>> getEventsByTimeAndType(@RequestParam Date time, @RequestParam String type) {
        return new ResponseEntity<>(eventService.findEventsByTimeAndType(time, type), HttpStatus.OK);
    }

    @GetMapping("/byTimeAndStatusOfImportance")
    public ResponseEntity<List<EventEntity>> getEventsByTimeAndStatusOfImportance(@RequestParam Date time, @RequestParam String statusOfImportance) {
        return new ResponseEntity<>(eventService.findEventsByTimeAndStatusOfImportance(time, statusOfImportance), HttpStatus.OK);
    }

    @GetMapping("/byTimeCountryAndType")
    public ResponseEntity<List<EventEntity>> getEventsByTimeCountryAndType(@RequestParam Date time, @RequestParam String country, @RequestParam String type) {
        return new ResponseEntity<>(eventService.findEventsByTimeCountryAndType(time, country, type), HttpStatus.OK);
    }

    @GetMapping("/byTimeTypeAndCountry")
    public ResponseEntity<List<EventEntity>> getEventsByTimeTypeAndCountry(@RequestParam Date time, @RequestParam String type, @RequestParam String country) {
        return new ResponseEntity<>(eventService.findEventsByTimeTypeAndCountry(time, type, country), HttpStatus.OK);
    }

    @GetMapping("/byTimeStatusOfImportanceAndCountry")
    public ResponseEntity<List<EventEntity>> getEventsByTimeStatusOfImportanceAndCountry(@RequestParam Date time, @RequestParam String statusOfImportance, @RequestParam String country) {
        return new ResponseEntity<>(eventService.findEventsByTimeStatusOfImportanceAndCountry(time, statusOfImportance, country), HttpStatus.OK);
    }

    @GetMapping("/byTimeCountryAndStatusOfImportance")
    public ResponseEntity<List<EventEntity>> getEventsByTimeCountryAndStatusOfImportance(@RequestParam Date time, @RequestParam String country, @RequestParam String statusOfImportance) {
        return new ResponseEntity<>(eventService.findEventsByTimeCountryAndStatusOfImportance(time, country, statusOfImportance), HttpStatus.OK);
    }


    @GetMapping("/linkedEventsByEventId")
    public ResponseEntity<List<LinkedEventEntity>> getLinkedEventsByEventId(@RequestParam String eventId) {
        return new ResponseEntity<>(linkedEventService.getLinkedEventsByEventId(eventId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteLinkedEvent")
    public ResponseEntity<Void> deleteLinkedEvent(@RequestParam String eventId, @RequestParam String linkedEventId) {
        linkedEventService.deleteLinkedEvent(eventId, linkedEventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


