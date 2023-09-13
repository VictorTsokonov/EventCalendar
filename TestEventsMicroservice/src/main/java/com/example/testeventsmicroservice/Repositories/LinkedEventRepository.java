package com.example.testeventsmicroservice.Repositories;

import com.example.testeventsmicroservice.Entities.LinkedEventEntity;

import java.util.List;

public interface LinkedEventRepository {

    LinkedEventEntity createLinkedEvent(String eventId, String linkedEventId);

    List<LinkedEventEntity> getLinkedEventsByEventId(String eventId);

    void deleteLinkedEvent(String eventId, String linkedEventId);
}