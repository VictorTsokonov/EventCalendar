package com.example.testeventsmicroservice.Services;

import com.example.testeventsmicroservice.Entities.LinkedEventEntity;
import com.example.testeventsmicroservice.Repositories.LinkedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedEventService {

    @Autowired
    private LinkedEventRepository linkedEventRepository;

    public LinkedEventEntity createLinkedEvent(String eventId, String linkedEventId) {
        return linkedEventRepository.createLinkedEvent(eventId, linkedEventId);
    }

    public List<LinkedEventEntity> getLinkedEventsByEventId(String eventId) {
        return linkedEventRepository.getLinkedEventsByEventId(eventId);
    }

    public void deleteLinkedEvent(String eventId, String linkedEventId) {
        linkedEventRepository.deleteLinkedEvent(eventId, linkedEventId);
    }
}