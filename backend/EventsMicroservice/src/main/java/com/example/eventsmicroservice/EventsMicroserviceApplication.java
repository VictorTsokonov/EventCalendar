package com.example.eventsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication()
public class EventsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsMicroserviceApplication.class, args);
    }

}
