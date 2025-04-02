package com.fcivillini.awesomePizzaManagerInterface.provider;

import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "PizzaMan provider", description = "API for managing pizzaMan pizza orders")
@RequestMapping("api/v1/pizza-man")
public interface PizzaManProvider {

    @Operation(summary = "Get new order details", description = "Retrieves the details of a new order")
    @GetMapping("/getNewOrder/")
    ResponseEntity<OrderRequestDto> getNewOrder() throws PizzaException;
}