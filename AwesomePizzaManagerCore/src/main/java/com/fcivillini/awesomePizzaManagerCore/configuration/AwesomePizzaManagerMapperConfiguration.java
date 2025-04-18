package com.fcivillini.awesomePizzaManagerCore.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan("com.fcivillini.awesomePizzaManagerCore.mapper")
public class AwesomePizzaManagerMapperConfiguration {

    public AwesomePizzaManagerMapperConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}
