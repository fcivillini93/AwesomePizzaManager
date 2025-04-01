package com.fcivillini.awesomePizzaManagerRepositoryMongo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@EnableMongoAuditing
@EntityScan("com.fcivillini.awesomePizzaManagerRepositoryMongo.entity")
@ComponentScan(basePackages = "com.fcivillini.awesomePizzaManagerRepositoryMongo.repository")
@EnableMongoRepositories(basePackages = {"com.fcivillini.awesomePizzaManagerRepositoryMongo.repository"})
public class AwesmePizzaManagerMongoRepositoryConfiguration {
    public AwesmePizzaManagerMongoRepositoryConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}

