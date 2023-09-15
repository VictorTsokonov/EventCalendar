package com.example.usersmicroservice.MariaDB;

import com.example.usersmicroservice.Entities.NoteEntity;
import com.example.usersmicroservice.Repositories.NoteRepository;
import com.example.usersmicroservice.RowMappers.NoteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

//public class MariaDBNoteRepository {
//}
@Repository
public class MariaDBNoteRepository implements NoteRepository {


    private final JdbcTemplate jdbcTemplate;

    private final NoteRowMapper noteRowMapper = new NoteRowMapper();


    @Autowired
    public MariaDBNoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Optional<NoteEntity> getNote(long id) {
        String sql = "SELECT * FROM notes WHERE id = ?";
        try {
            NoteEntity note = jdbcTemplate.queryForObject(sql, noteRowMapper, id);
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

//    @Override
//    public List<NoteEntity> getAllNotesByUserId(String userId) {
//        return null;
//    }

    public NoteEntity createNote(NoteEntity noteEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO notes (user_id, event_name, text) VALUES (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, noteEntity.userId());
            ps.setString(2, noteEntity.eventName());
            ps.setString(3, noteEntity.text());
            return ps;
        }, keyHolder);

        long noteId = keyHolder.getKey().longValue();
        return getNote(keyHolder.getKey().longValue()).get();
    }



    @Override
    public List<NoteEntity> getAllNotesByUserId(String userId) {
        String sql = "SELECT * FROM notes WHERE user_id = ?";
        return jdbcTemplate.query(sql, new NoteRowMapper(), userId);
    }

    @Override
    public void deleteNoteByUserIdAndId(long userId, long id) {
        String sql = "DELETE FROM notes WHERE user_id = ? AND id = ?";
        jdbcTemplate.update(sql, userId, id);
    }
    // ... (implement other methods like update, delete, etc.) ...
}
