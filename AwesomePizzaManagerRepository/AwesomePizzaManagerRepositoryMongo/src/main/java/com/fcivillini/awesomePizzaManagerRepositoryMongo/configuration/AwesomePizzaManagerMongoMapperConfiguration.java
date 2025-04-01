package com.fcivillini.awesomePizzaManagerRepositoryMongo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan("com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper")
public class AwesomePizzaManagerMongoMapperConfiguration {
    public AwesomePizzaManagerMongoMapperConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}
