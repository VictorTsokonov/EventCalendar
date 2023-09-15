package com.example.usersmicroservice.Services;

import com.example.usersmicroservice.Entities.UserEntity;
import com.example.usersmicroservice.MariaDB.MariaDBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final MariaDBUserRepository userRepository;

    @Autowired
    public UserService(MariaDBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.createUser(userEntity);
    }

    public Optional<UserEntity> getUser(long id) {
        return userRepository.getUser(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ... (implement other methods like update, delete, etc.) ...
}
