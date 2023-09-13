package com.example.kafkaproducermicroservice.Services;

import com.example.kafkaproducermicroservice.Entities.Event;
import com.example.kafkaproducermicroservice.Generator.RandomEventGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventProducerService {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Autowired
    public EventProducerService(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvents(List<Event> events) {
        for (Event event : events) {
            kafkaTemplate.send("events", event.country(), event);
            System.out.println("Produced event: " + event);
        }
    }

    @Scheduled(fixedRate = 10000)  // schedule to run every 10 seconds
    public void produceRandomEventsAutomatically() {
        List<Event> events = RandomEventGenerator.generateRandomEvents(5); // Adjust the number of events as needed
        sendEvents(events);
    }
}