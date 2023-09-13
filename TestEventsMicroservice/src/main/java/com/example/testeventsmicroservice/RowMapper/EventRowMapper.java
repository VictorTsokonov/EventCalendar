package com.example.testeventsmicroservice.RowMapper;

import com.example.testeventsmicroservice.Entities.EventEntity;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EventRowMapper implements RowMapper<EventEntity> {

    @Override
    public EventEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EventEntity(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("country"),
                rs.getString("type"),
                rs.getString("description"),
                rs.getString("status_of_importance"),
                new Date(rs.getTimestamp("time").getTime()), // Convert Timestamp to Date
                rs.getDouble("actual"),
                rs.getDouble("forecast"),
                rs.getDouble("previous")
        );
    }
}