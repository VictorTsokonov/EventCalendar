package com.example.usersmicroservice.Repositories;

import com.example.usersmicroservice.Entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    UserEntity createUser(UserEntity userEntity);

    Optional<UserEntity> getUser(long id);

    UserEntity updateUser(UserEntity userEntity);

    void deleteUser(String id);

    List<UserEntity> getAllUsers();

    public boolean existsByUsername(String username);

    public Optional<UserEntity> findByUsername(String username);
}