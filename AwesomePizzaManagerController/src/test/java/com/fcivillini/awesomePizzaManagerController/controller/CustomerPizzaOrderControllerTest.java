package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.service.AwesomePizzaManagerService;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
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
class CustomerPizzaOrderControllerTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;

    @Mock
    private AwesomePizzaManagerService awesomePizzaManagerService;

    @InjectMocks
    private CustomerPizzaOrderController customerPizzaOrderController;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRequestMapper, awesomePizzaManagerService);
    }

    @Test
    @SneakyThrows
    void test_create() {

        OrderRequestDto orderRequestDto = new OrderRequestDto().setUserName("user");
        OrderRequest orderQuest = new OrderRequest().setUserName("user");
        when(orderRequestMapper.fromDto(orderRequestDto)).thenReturn(orderQuest);
        when(awesomePizzaManagerService.create(orderQuest)).thenReturn("id-1");
        assertEquals(new ResponseEntity<>("id-1", HttpStatus.CREATED), customerPizzaOrderController.createOrder(orderRequestDto));

    }
}