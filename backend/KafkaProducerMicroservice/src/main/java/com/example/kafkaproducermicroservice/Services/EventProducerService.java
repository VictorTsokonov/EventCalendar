package com.example.kafkaproducermicroservice.Services;

import com.example.kafkaproducermicroservice.Entities.Event;
import com.example.kafkaproducermicroservice.Generator.RandomEventGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EventProducerService {
    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Autowired
    public EventProducerService(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Event event) {
        kafkaTemplate.send("events", event.getCountry(), event);
        System.out.println("Produced event: " + event);
    }

    @Scheduled(fixedRate = 10000)  // schedule to run every 10 seconds
    public void produceRandomEventAutomatically() {
        Event event = RandomEventGenerator.generateRandomEvent();
        sendEvent(event);
    }
}
