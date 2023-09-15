package com.example.testeventsmicroservice.Repositories;

import com.example.testeventsmicroservice.Entities.EventEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository {

    EventEntity createEvent(String id, String name, String country, String type, String description, String statusOfImportance, Date time, Double actual, Double forecast, Double previous);

    @Transactional
    EventEntity createEvent(EventEntity eventEntity);

    Optional<EventEntity> getEvent(String id);

    List<EventEntity> listEventsByMonthAndYear(int month, int year);

    List<EventEntity> getEventsByMonthAndYear(Date time);

    List<EventEntity> findEventsByTimeAndCountry(Date time, String country);

    List<EventEntity> findEventsByTimeAndType(Date time, String type);

    List<EventEntity> findEventsByTimeAndStatusOfImportance(Date time, String statusOfImportance);

    List<EventEntity> findEventsByTimeCountryAndType(Date time, String country, String type);

    List<EventEntity> findEventsByTimeTypeAndCountry(Date time, String type, String country);

    List<EventEntity> findEventsByTimeStatusOfImportanceAndCountry(Date time, String statusOfImportance, String country);

    List<EventEntity> findEventsByTimeCountryAndStatusOfImportance(Date time, String country, String statusOfImportance);

    @Transactional(readOnly = true)
    List<EventEntity> getEventsByTime(Date time);

    @Transactional(readOnly = true)
    List<EventEntity> getEventsByTimeAndCountry(Date time, String country);

    void deleteEvent(String id);

    EventEntity updateEvent(EventEntity eventEntity);

    List<EventEntity> findEventsByCountryAndTypeAndName(String country, String type, String name);

    // Implement fetch by country, type and status of importance
    List<EventEntity> findEventsByCountryAndTypeAndStatusOfImportance(String country, String type, String statusOfImportance, int year, int month);

}