package com.fcivillini.awesomePizzaManagerCore.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan("com.fcivillini.awesomePizzaManagerCore.service.impl")
public class AwesomePizzaManagerServiceConfiguration {

    public AwesomePizzaManagerServiceConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}
