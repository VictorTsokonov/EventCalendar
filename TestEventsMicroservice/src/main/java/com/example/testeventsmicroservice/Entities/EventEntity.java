package com.example.testeventsmicroservice.Entities;

import java.util.Date;

public record EventEntity(
        String id,
        String name,
        String country,
        String type,
        String description,
        String statusOfImportance,
        Date time,
        Double actual,
        Double forecast,
        Double previous
) {
}
