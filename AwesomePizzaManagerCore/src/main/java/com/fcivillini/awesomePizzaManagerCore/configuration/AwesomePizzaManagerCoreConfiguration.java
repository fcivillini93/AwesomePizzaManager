package com.fcivillini.awesomePizzaManagerCore.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({
        AwesomePizzaManagerMapperConfiguration.class,
        AwesomePizzaManagerServiceConfiguration.class,
        AwesomePizzaManagerValidatorConfiguration.class
})
public class AwesomePizzaManagerCoreConfiguration {

    public AwesomePizzaManagerCoreConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }
}
