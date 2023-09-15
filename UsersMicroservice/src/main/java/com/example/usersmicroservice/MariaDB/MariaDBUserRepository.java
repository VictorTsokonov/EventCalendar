package com.example.usersmicroservice.MariaDB;

import com.example.usersmicroservice.Entities.UserEntity;
import com.example.usersmicroservice.Repositories.UserRepository;
import com.example.usersmicroservice.RowMappers.UserRowMapper;
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

//public class MariaDBUserRepository {
//}
@Repository
public class MariaDBUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    @Autowired
    public MariaDBUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userEntity.username());
            ps.setString(2, userEntity.password());
            return ps;
        }, keyHolder);
//        jdbcTemplate.update(sql, userEntity.username(), userEntity.password());


        return getUser(keyHolder.getKey().longValue()).get();
    }

    @Override
    public Optional<UserEntity> getUser(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            UserEntity user = jdbcTemplate.queryForObject(sql, userRowMapper, id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }

    // ... (implement other methods like update, delete, existsByUsername, findByUsername, etc.) ...

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            UserEntity user = jdbcTemplate.queryForObject(sql, userRowMapper, username);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // ... (implement remaining methods) ...
}


