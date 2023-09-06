package com.example.eventsmicroservice.Repositories;

import com.example.eventsmicroservice.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTimeBetween(Date start, Date end);

}

