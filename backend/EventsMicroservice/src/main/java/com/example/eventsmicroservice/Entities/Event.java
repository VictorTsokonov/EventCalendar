package com.example.eventsmicroservice.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events", indexes = {
        @Index(columnList = "time"),
        @Index(columnList = "type"),
        @Index(columnList = "country"),
        @Index(columnList = "statusOfImportance")
})
public record Event(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String name,
        String country,
        String type,
        String description,
        String statusOfImportance,
        @Temporal(TemporalType.TIMESTAMP) Date time,
        Double actual,
        Double forecast,
        Double previous
) {}

