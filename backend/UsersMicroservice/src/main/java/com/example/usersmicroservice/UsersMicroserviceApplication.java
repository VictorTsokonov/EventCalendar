package com.example.usersmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.usersmicroservice")
public class UsersMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersMicroserviceApplication.class, args);
    }

}
