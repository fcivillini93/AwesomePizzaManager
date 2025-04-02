package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.service.impl.PizzaManManagerService;
import com.fcivillini.awesomePizzaManagerCore.validator.PizzaManManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaManControllerTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;

    @Mock
    private PizzaManManagerService pizzaManManagerService;

    @Mock
    private PizzaManManagerValidator pizzaManManagerValidator;

    @InjectMocks
    private PizzaManController pizzaManController;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRequestMapper, pizzaManManagerService, pizzaManManagerValidator);
    }

    @Test
    @SneakyThrows
    void test_create() {

        OrderRequest orderQuest = new OrderRequest().setUserName("user");
        OrderRequestDto orderRequestDto = new OrderRequestDto().setUserName("user");
        when(pizzaManManagerService.findNewOrder()).thenReturn(orderQuest);
        when(orderRequestMapper.toDto(orderQuest)).thenReturn(orderRequestDto);
        assertEquals(new ResponseEntity<>(orderRequestDto, HttpStatus.OK), pizzaManController.getNewOrder());

    }

}