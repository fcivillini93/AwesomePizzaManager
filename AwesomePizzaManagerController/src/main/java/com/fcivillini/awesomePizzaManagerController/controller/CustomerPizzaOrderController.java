package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.service.AwesomePizzaManagerService;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerInterface.provider.CustomerPizzaOrderProvider;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomerPizzaOrderController implements CustomerPizzaOrderProvider {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private AwesomePizzaManagerService awesomePizzaManagerService;


    @Override
    public ResponseEntity<String> createOrder(@Valid final OrderRequestDto orderRequestDto) throws PizzaException {
        return new ResponseEntity<>(awesomePizzaManagerService.create(orderRequestMapper.fromDto(orderRequestDto)) , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderRequestDto> getOrder(String orderId) throws PizzaException {
        log.info("Retrieving order with ID: {}", orderId);
        // Implement the logic to retrieve an order by ID
        throw new PizzaException("todo", HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> payOrder(String orderId) throws PizzaException {
        log.info("Paying for order with ID: {}", orderId);
        // Implement the logic to mark an order as paid
        throw new PizzaException("todo", HttpStatus.NOT_IMPLEMENTED);
    }
}