package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.service.OrderRequestManagerService;
import com.fcivillini.awesomePizzaManagerCore.validator.OrderRequestManagerValidator;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerPizzaOrderControllerTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;

    @Mock
    private OrderRequestManagerService orderRequestManagerService;

    @Mock
    private OrderRequestManagerValidator orderRequestManagerValidator;

    @InjectMocks
    private CustomerPizzaOrderController customerPizzaOrderController;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRequestMapper, orderRequestManagerService);
    }

    @Test
    @SneakyThrows
    void test_create() {

        OrderRequestDto orderRequestDto = new OrderRequestDto().setUserName("user");
        OrderRequest orderQuest = new OrderRequest().setUserName("user");
        when(orderRequestMapper.fromDto(orderRequestDto)).thenReturn(orderQuest);
        when(orderRequestManagerService.create(orderQuest)).thenReturn("id-1");
        assertEquals(new ResponseEntity<>("id-1", HttpStatus.CREATED), customerPizzaOrderController.createOrder(orderRequestDto));

    }

    @Test
    @SneakyThrows
    void test_findById() {
        OrderRequest orderRequest = new OrderRequest().setId("orderId");
        OrderRequestDto orderRequestDto = new OrderRequestDto().setId("orderId");
        when(orderRequestManagerService.findOrder("orderId")).thenReturn(orderRequest);
        when(orderRequestMapper.toDto(orderRequest)).thenReturn(orderRequestDto);
        assertEquals(new ResponseEntity(orderRequestDto, HttpStatus.OK), customerPizzaOrderController.getOrder("orderId"));
    }


    @Test
    @SneakyThrows
    void test_payOrder() {
        doNothing().when(orderRequestManagerService).payOrder("orderId");
        assertEquals(new ResponseEntity(HttpStatus.OK), customerPizzaOrderController.payOrder("orderId"));
    }
}