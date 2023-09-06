package com.example.eventsmicroservice.Services;

import com.example.eventsmicroservice.Entities.Event;
import com.example.eventsmicroservice.Entities.EventRelation;
import com.example.eventsmicroservice.Repositories.EventRelationRepository;
import com.example.eventsmicroservice.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRelationRepository eventRelationRepository;

    public List<Event> getEventsForMonth(Date start, Date end) {
        return eventRepository.findByTimeBetween(start, end);
    }

    public List<EventRelation> getEventRelations(Long mainEventId) {
        return eventRelationRepository.findByMainEventId(mainEventId);
    }

    // Other service methods as needed...
}
