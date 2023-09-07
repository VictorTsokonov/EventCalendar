package com.example.kafkaproducermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerMicroserviceApplication.class, args);
    }

}
