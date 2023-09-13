package com.example.testeventsmicroservice.MariaDB;

import com.example.testeventsmicroservice.Entities.LinkedEventEntity;
import com.example.testeventsmicroservice.Repositories.LinkedEventRepository;
import com.example.testeventsmicroservice.RowMapper.LinkedEventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MariaDBLinkedEventRepository implements LinkedEventRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MariaDBLinkedEventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public LinkedEventEntity createLinkedEvent(String eventId, String linkedEventId) {
        String sql = "INSERT INTO linked_events (event_id, linked_event_id) VALUES (?, ?), (?, ?)";
        jdbcTemplate.update(sql, eventId, linkedEventId, linkedEventId, eventId);

        // Assuming LinkedEventEntity has a constructor that accepts eventId and linkedEventId
        return new LinkedEventEntity(eventId, linkedEventId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LinkedEventEntity> getLinkedEventsByEventId(String eventId) {
        String sql = "SELECT * FROM linked_events WHERE event_id = ?";
        return jdbcTemplate.query(sql, new LinkedEventRowMapper(), eventId);
    }

    @Override
    @Transactional
    public void deleteLinkedEvent(String eventId, String linkedEventId) {
        String sql = "DELETE FROM linked_events WHERE (event_id = ? AND linked_event_id = ?) OR (event_id = ? AND linked_event_id = ?)";
        jdbcTemplate.update(sql, eventId, linkedEventId, linkedEventId, eventId);
    }

    // Additional methods to update and retrieve single linked event can be added here.
}