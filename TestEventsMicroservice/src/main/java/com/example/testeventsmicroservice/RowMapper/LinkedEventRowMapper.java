package com.example.testeventsmicroservice.RowMapper;

import com.example.testeventsmicroservice.Entities.LinkedEventEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkedEventRowMapper implements RowMapper<LinkedEventEntity> {

    @Override
    public LinkedEventEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LinkedEventEntity(
                rs.getString("event_id"),
                rs.getString("linked_event_id")
        );
    }
}