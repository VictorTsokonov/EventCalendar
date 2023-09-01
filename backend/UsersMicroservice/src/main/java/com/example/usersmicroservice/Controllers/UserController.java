package com.example.usersmicroservice.Controllers;


import com.example.usersmicroservice.Repositories.UserRepository;
import com.example.usersmicroservice.Tables.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> addUser(@Valid @RequestBody User user) {
        if (userRepository.existsById(user.getEmail())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Email already exists"));
        }

        User updatedUser = userRepository.save(user);
        Map<String, String> responseBody = Collections.singletonMap("email", updatedUser.getEmail());
        return ResponseEntity.ok(responseBody);
    }


    @PostMapping("/signin")
    public ResponseEntity<String> SignIn(@Valid @RequestBody User user){
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();

        Optional<User> dbUser = userRepository.findById(userEmail);
        if(dbUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user doesn't exist.");
        }
        if(!userPassword.equals(dbUser.get().getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        }
        return ResponseEntity.ok(userEmail);
    }


    @GetMapping("/user")
    public Optional<User> getUser(@RequestParam String email) {
        return userRepository.findById(email);
    }

}
//    @DeleteMapping("/user/{email}")
//    public ResponseEntity deleteOneMovie(@PathVariable("id") String id) {
//        movieDetailsRepository.deleteById(id);
//        return ResponseEntity.accepted().build();
//    }
