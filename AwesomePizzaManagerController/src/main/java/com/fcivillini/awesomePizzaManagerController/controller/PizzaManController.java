package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.service.impl.PizzaManManagerService;
import com.fcivillini.awesomePizzaManagerCore.validator.PizzaManManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerInterface.provider.PizzaManProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PizzaManController implements PizzaManProvider {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private PizzaManManagerService pizzaManManagerService;

    @Autowired
    private PizzaManManagerValidator pizzaManManagerValidator;

    @Override
    public ResponseEntity<OrderRequestDto> getNewOrder() throws PizzaException {
        pizzaManManagerValidator.validateFindNewOrder();
        return new ResponseEntity<>(orderRequestMapper.toDto(pizzaManManagerService.findNewOrder()), HttpStatus.OK);
    }

}