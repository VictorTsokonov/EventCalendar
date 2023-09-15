package com.example.kafkaconsumermicroservice.Services;

import com.example.kafkaconsumermicroservice.Entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventConsumerService {


    private final RestTemplate restTemplate;
    @Autowired
    public EventConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @KafkaListener(topics = "events", groupId = "events2", containerFactory = "kafkaListenerContainerFactory")
    public void consumeEvent(Event event) {
        System.out.println("Consumed event: " + event);

        String endpointUrl = "http://localhost:8080/events/createOrUpdateEvent";

        restTemplate.postForObject(endpointUrl, event, Event.class);
        System.out.println("Event sent to endpoint: " + event);
    }
}
