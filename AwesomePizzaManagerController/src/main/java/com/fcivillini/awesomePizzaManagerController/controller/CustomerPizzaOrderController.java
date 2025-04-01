package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.service.OrderRequestManagerService;
import com.fcivillini.awesomePizzaManagerCore.validator.OrderRequestManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerInterface.provider.CustomerPizzaOrderProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private OrderRequestManagerService orderRequestManagerService;

    @Autowired
    private OrderRequestManagerValidator orderRequestManagerValidator;


    @Override
    public ResponseEntity<String> createOrder(@Valid final OrderRequestDto orderRequestDto) throws PizzaException {
        return new ResponseEntity<>(orderRequestManagerService.create(orderRequestMapper.fromDto(orderRequestDto)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderRequestDto> getOrder(@NotNull @NotEmpty String orderId) throws PizzaException {
        orderRequestManagerValidator.validateGetOrderRequest(orderId);
        return new ResponseEntity<>(orderRequestMapper.toDto(orderRequestManagerService.findOrder(orderId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> payOrder(@NotNull @NotEmpty String orderId) throws PizzaException {
        orderRequestManagerValidator.validatePayOrderRequest(orderId);
        orderRequestManagerService.payOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}