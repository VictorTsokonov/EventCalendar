package com.example.eventsmicroservice.Repositories;

import com.example.eventsmicroservice.Entities.EventRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRelationRepository extends JpaRepository<EventRelation, Long> {
    List<EventRelation> findByMainEventId(Long mainEventId);
    // Other query methods as required...
}
