package com.fcivillini.awesomePizzaManagerBoot;

import com.fcivillini.awesomePizzaManagerController.configuration.AwesomePizzaManagerControllerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        AwesomePizzaManagerControllerConfiguration.class
})
@SpringBootApplication
public class AwesomePizzaManagerBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwesomePizzaManagerBootApplication.class, args);
    }

}

