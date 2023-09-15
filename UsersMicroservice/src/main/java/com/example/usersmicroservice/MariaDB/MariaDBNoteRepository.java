package com.example.usersmicroservice.MariaDB;

import com.example.usersmicroservice.Entities.NoteEntity;
import com.example.usersmicroservice.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

//public class MariaDBNoteRepository {
//}
@Repository
public class MariaDBNoteRepository implements NoteRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MariaDBNoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NoteEntity createNote(NoteEntity noteEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO notes (user_id, text) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, noteEntity.userId());
            ps.setString(2, noteEntity.text());
            return ps;
        }, keyHolder);

        long noteId = keyHolder.getKey().longValue();
        return getNote(noteId).orElseThrow(() -> new RuntimeException("Note creation failed"));
    }

    public Optional<NoteEntity> getNote(long id) {
        String sql = "SELECT * FROM notes WHERE id = ?";
        try {
            NoteEntity note = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(NoteEntity.class), id);
            return Optional.ofNullable(note);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public NoteEntity updateNote(NoteEntity noteEntity) {
        return null;
    }

    @Override
    public void deleteNote(String id) {

    }

    @Override
    public List<NoteEntity> getAllNotesByUserId(String userId) {
        return null;
    }
    // ... (implement other methods like update, delete, etc.) ...
}
