package com.fcivillini.awesomePizzaManagerController.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({
        AwesomePizzaManagerServiceConfiguration.class
})
@ComponentScan(basePackages = "com.fcivillini.awesomePizzaManagerController.controller")
public class AwesomePizzaManagerControllerConfiguration {

    public AwesomePizzaManagerControllerConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}
