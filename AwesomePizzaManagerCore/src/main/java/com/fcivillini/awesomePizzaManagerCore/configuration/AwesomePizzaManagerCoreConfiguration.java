package com.fcivillini.awesomePizzaManagerCore.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AwesomePizzaManagerCoreConfiguration {

    public AwesomePizzaManagerCoreConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}
