package com.example.usersmicroservice.Controllers;

import com.example.usersmicroservice.Entities.UserEntity;
import com.example.usersmicroservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {


    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) {
        if (userService.findByUsername(userEntity.username()).isPresent()) {
            return null;
        }

        UserEntity updatedUser = userService.createUser(userEntity);

        return ResponseEntity.ok(updatedUser);
    }



    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserEntity userEntity) {
        String username = userEntity.username();
        String userPassword = userEntity.password();

        Optional<UserEntity> dbUser = userService.findByUsername(username);
        if (dbUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user doesn't exist.");
        }
        if (!userPassword.equals(dbUser.get().password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
        return ResponseEntity.ok(username);
    }

    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser(@RequestParam String username) {
        return userService.findByUsername(username)
                .map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ... (implement other endpoints like update, delete, etc.) ...
}

