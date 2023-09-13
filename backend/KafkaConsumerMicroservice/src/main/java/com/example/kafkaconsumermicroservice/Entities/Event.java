package com.example.kafkaconsumermicroservice.Entities;

import java.util.Date;
import java.util.List;

public record Event(
        String id,
        String name,
        String country,
        String type,
        String description,
        String statusOfImportance,
        Date time,
        Double actual,
        Double forecast,
        Double previous,
        List<String> linkedEventsIds
){}
