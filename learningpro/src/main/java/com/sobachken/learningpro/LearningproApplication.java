package com.sobachken.learningpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class LearningproApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningproApplication.class, args);
    }

}
