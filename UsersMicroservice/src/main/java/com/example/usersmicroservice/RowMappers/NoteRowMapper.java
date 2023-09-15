package com.example.usersmicroservice.RowMappers;

import com.example.usersmicroservice.Entities.NoteEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteRowMapper implements RowMapper<NoteEntity> {
    @Override
    public NoteEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new NoteEntity(
                rs.getLong("id"),
                rs.getLong("userId"),
                rs.getString("text")
        );
    }
}