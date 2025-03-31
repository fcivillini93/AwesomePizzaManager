package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.provider.CustomerPizzaOrderProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomerPizzaOrderController implements CustomerPizzaOrderProvider {


    @Override
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("Creating order: {}", orderRequestDto);
        // Implement the logic to create an order
        return orderRequestDto;
    }

    @Override
    public OrderRequestDto getOrder(String orderId) {
        log.info("Retrieving order with ID: {}", orderId);
        // Implement the logic to retrieve an order by ID
        return new OrderRequestDto(); // Return a dummy order for now
    }

    @Override
    public void payOrder(String orderId) {
        log.info("Paying for order with ID: {}", orderId);
        // Implement the logic to mark an order as paid
    }
}