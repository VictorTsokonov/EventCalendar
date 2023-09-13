package com.example.testeventsmicroservice.Services;

import com.example.testeventsmicroservice.Entities.EventEntity;
import com.example.testeventsmicroservice.Entities.EventKafkaEntity;
import com.example.testeventsmicroservice.Entities.LinkedEventEntity;
import com.example.testeventsmicroservice.Repositories.EventRepository;
import com.example.testeventsmicroservice.Repositories.LinkedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;


    private final LinkedEventRepository linkedEventRepository;

    @Autowired
    public EventService(EventRepository eventRepository, LinkedEventRepository linkedEventRepository) {
        this.eventRepository = eventRepository;
        this.linkedEventRepository = linkedEventRepository;
    }

    @Transactional
    public EventEntity createOrUpdateEvent(EventKafkaEntity eventKafkaEntity) {
        List<EventEntity> existingEvents = eventRepository.findEventsByCountryAndTypeAndName(eventKafkaEntity.country(), eventKafkaEntity.type(), eventKafkaEntity.name());

        if (!existingEvents.isEmpty()) {
            EventEntity existingEvent = existingEvents.get(0);
            existingEvent = updateEventDetails(existingEvent, eventKafkaEntity);
            System.out.println(eventKafkaEntity);

            System.out.println(existingEvent.id());
            System.out.println(eventKafkaEntity.id());

//            createLinkedEvents(eventKafkaEntity);
            return eventRepository.updateEvent(existingEvent);
        } else {
            EventEntity newEvent = createNewEventFromKafkaEntity(eventKafkaEntity);
            eventRepository.createEvent(newEvent);
            System.out.println("IN HERE");
            createLinkedEvents(eventKafkaEntity);
            System.out.println("Out of here");
            return newEvent;
        }
    }

    private EventEntity createNewEventFromKafkaEntity(EventKafkaEntity eventKafkaEntity) {
        return new EventEntity(
                eventKafkaEntity.id(),
                eventKafkaEntity.name(),
                eventKafkaEntity.country(),
                eventKafkaEntity.type(),
                eventKafkaEntity.description(),
                eventKafkaEntity.statusOfImportance(),
                new Date(eventKafkaEntity.time()),
                eventKafkaEntity.actual(),
                eventKafkaEntity.forecast(),
                eventKafkaEntity.previous()
        );
    }

    private EventEntity updateEventDetails(EventEntity existingEvent, EventKafkaEntity eventKafkaEntity) {
        return new EventEntity(
                existingEvent.id(),
                eventKafkaEntity.name(),
                eventKafkaEntity.country(),
                eventKafkaEntity.type(),
                eventKafkaEntity.description(),
                eventKafkaEntity.statusOfImportance(),
                new Date(eventKafkaEntity.time()),
                eventKafkaEntity.actual(),
                eventKafkaEntity.forecast(),
                eventKafkaEntity.previous()
        );
    }


    @Transactional
    public void createLinkedEvents(EventKafkaEntity eventKafkaEntity) {
        String eventId = eventKafkaEntity.id();
        List<String> linkedEventsIds = eventKafkaEntity.linkedEventsIds();

        for (String linkedEventId : linkedEventsIds) {
            eventRepository.getEvent(linkedEventId).ifPresent(linkedEvent -> {
                if (isLinkedEventNotPresent(eventId, linkedEventId)) {
                    linkedEventRepository.createLinkedEvent(eventId, linkedEventId);
                }
            });
        }
    }

    private boolean isLinkedEventNotPresent(String eventId, String linkedEventId) {
        List<LinkedEventEntity> linkedEvents = linkedEventRepository.getLinkedEventsByEventId(eventId);
        return linkedEvents.stream().noneMatch(linkedEvent -> linkedEvent.linkedEventId().equals(linkedEventId));
    }












    public List<EventEntity> getEventsByTime(LocalDateTime time) {
        int month = time.getMonthValue(); // Gets the month (1-12)
        int year = time.getYear(); // Gets the year
        return eventRepository.listEventsByMonthAndYear(month, year);
    }



    public List<EventEntity> getEventsByTimeAndCountry(Date time, String country) {
        return eventRepository.getEventsByTimeAndCountry(time, country);
    }


    public List<EventEntity> findEventsByTimeAndType(Date time, String type) {
        return eventRepository.findEventsByTimeAndType(time, type);
    }


    public List<EventEntity> findEventsByTimeAndStatusOfImportance(Date time, String statusOfImportance) {
        return eventRepository.findEventsByTimeAndStatusOfImportance(time, statusOfImportance);
    }


    public List<EventEntity> findEventsByTimeCountryAndType(Date time, String country, String type) {
        return eventRepository.findEventsByTimeCountryAndType(time, country, type);
    }


    public List<EventEntity> findEventsByTimeTypeAndCountry(Date time, String type, String country) {
        return eventRepository.findEventsByTimeTypeAndCountry(time, type, country);
    }


    public List<EventEntity> findEventsByTimeStatusOfImportanceAndCountry(Date time, String statusOfImportance, String country) {
        return eventRepository.findEventsByTimeStatusOfImportanceAndCountry(time, statusOfImportance, country);
    }


    public List<EventEntity> findEventsByTimeCountryAndStatusOfImportance(Date time, String country, String statusOfImportance) {
        return eventRepository.findEventsByTimeCountryAndStatusOfImportance(time, country, statusOfImportance);
    }



}

