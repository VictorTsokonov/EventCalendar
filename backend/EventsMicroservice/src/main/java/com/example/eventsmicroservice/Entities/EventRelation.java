package com.example.eventsmicroservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "event_relations")
public record EventRelation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        @ManyToOne @JoinColumn(name = "main_event_id") Event mainEvent,
        @ManyToOne @JoinColumn(name = "linked_event_id") Event linkedEvent
) {}

