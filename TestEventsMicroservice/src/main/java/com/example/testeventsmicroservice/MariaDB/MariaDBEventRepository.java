package com.example.testeventsmicroservice.MariaDB;

import com.example.testeventsmicroservice.Entities.EventEntity;
import com.example.testeventsmicroservice.Repositories.EventRepository;
import com.example.testeventsmicroservice.RowMapper.EventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MariaDBEventRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MariaDBEventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EventEntity createEvent(String id, String name, String country, String type, String description, String statusOfImportance, Date time, Double actual, Double forecast, Double previous) {
        String sql = "INSERT INTO events (id, name, country, type, description, status_of_importance, time, actual, forecast, previous) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, name, country, type, description, statusOfImportance, time, actual, forecast, previous);
        return getEvent(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    @Transactional
    public EventEntity createEvent(EventEntity eventEntity) {
        String sql = "INSERT INTO events (id, name, country, type, description, status_of_importance, time, actual, forecast, previous) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, eventEntity.id(), eventEntity.name(), eventEntity.country(), eventEntity.type(), eventEntity.description(), eventEntity.statusOfImportance(), eventEntity.time(), eventEntity.actual(), eventEntity.forecast(), eventEntity.previous());
        return getEvent(eventEntity.id())
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EventEntity> getEvent(String eventId) {
        try {
            String sql = "SELECT * FROM events WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{eventId}, new EventRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }



    @Override
    public List<EventEntity> listEventsByMonthAndYear(int month, int year) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = ? AND MONTH(time) = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), year, month);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventEntity> getEventsByMonthAndYear(Date time) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?)";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time);
    }

    @Override
    public List<EventEntity> findEventsByTimeAndCountry(Date time, String country) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND country = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, country);
    }

    @Override
    public List<EventEntity> findEventsByTimeAndType(Date time, String type) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND type = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, type);
    }

    @Override
    public List<EventEntity> findEventsByTimeAndStatusOfImportance(Date time, String statusOfImportance) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND status_of_importance = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, statusOfImportance);
    }

    @Override
    public List<EventEntity> findEventsByCountryAndTypeAndName(String country, String type, String name) {
        String sql = "SELECT * FROM events WHERE country = ? AND type = ? AND name = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), country, type, name);
    }

    @Override
    public List<EventEntity> findEventsByTimeCountryAndType(Date time, String country, String type) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND country = ? AND type = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, country, type);
    }

    @Override
    public List<EventEntity> findEventsByTimeTypeAndCountry(Date time, String type, String country) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND type = ? AND country = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, type, country);
    }

    @Override
    public List<EventEntity> findEventsByTimeStatusOfImportanceAndCountry(Date time, String statusOfImportance, String country) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND status_of_importance = ? AND country = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, statusOfImportance, country);
    }

    @Override
    public List<EventEntity> findEventsByTimeCountryAndStatusOfImportance(Date time, String country, String statusOfImportance) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND country = ? AND status_of_importance = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, country, statusOfImportance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventEntity> getEventsByTime(Date time) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?)";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventEntity> getEventsByTimeAndCountry(Date time, String country) {
        String sql = "SELECT * FROM events WHERE YEAR(time) = YEAR(?) AND MONTH(time) = MONTH(?) AND country = ?";
        return jdbcTemplate.query(sql, new EventRowMapper(), time, time, country);
    }

    // Implement other methods here...

    @Override
    @Transactional
    public void deleteEvent(String id) {
        String sql = "DELETE FROM events WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    @Transactional
    public EventEntity updateEvent(EventEntity eventEntity) {
        String sql = "UPDATE events SET description = ?, status_of_importance = ?, time = ?, actual = ?, forecast = ?, previous = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                eventEntity.description(),
                eventEntity.statusOfImportance(),
                eventEntity.time(),
                eventEntity.actual(),
                eventEntity.forecast(),
                eventEntity.previous(),
                eventEntity.id()); // Use the id to identify the correct record to update

        return eventEntity;
    }



}