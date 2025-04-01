package com.fcivillini.awesomePizzaManagerInterface.provider;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "PizzaMan provider", description = "API for managing pizzaMan pizza orders")
@RequestMapping("api/v1/pizza-man")
public interface PizzaManProvider {

}