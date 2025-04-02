package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.EvolveOrderMapper;
import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.service.PizzaManManagerService;
import com.fcivillini.awesomePizzaManagerCore.validator.PizzaManManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.dto.EvolveOrderDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerInterface.provider.PizzaManProvider;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PizzaManController implements PizzaManProvider {

    @Autowired
    private EvolveOrderMapper evolveOrderMapper;

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private PizzaManManagerService pizzaManManagerService;

    @Autowired
    private PizzaManManagerValidator pizzaManManagerValidator;

    @Override
    public ResponseEntity<OrderRequestDto> getNewOrder() throws PizzaException {
        pizzaManManagerValidator.findNewOrder();
        return new ResponseEntity<>(orderRequestMapper.toDto(pizzaManManagerService.findNewOrder()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderRequestDto> evolveOrder(@Valid EvolveOrderDto evolveOrderDto) throws PizzaException {
        EvolveOrder evolveOrderRequest = evolveOrderMapper.fromDto(evolveOrderDto);
        OrderRequest orderRequest = pizzaManManagerValidator.validateEvolveOrder(evolveOrderRequest);
        return new ResponseEntity<>(orderRequestMapper.toDto(pizzaManManagerService.evolveOrder(orderRequest, evolveOrderRequest)), HttpStatus.OK);
    }

}