package com.example.testeventsmicroservice.Entities;

import java.util.List;

public record EventKafkaEntity(
        String id,
        String name,
        String country,
        String type,
        String description,
        String statusOfImportance,
        long time,
        double actual,
        double forecast,
        double previous,
        List<String> linkedEventsIds
) {}
