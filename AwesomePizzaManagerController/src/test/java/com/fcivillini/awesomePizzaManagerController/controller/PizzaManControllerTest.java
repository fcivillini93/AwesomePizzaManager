package com.fcivillini.awesomePizzaManagerController.controller;

import com.fcivillini.awesomePizzaManagerCore.mapper.EvolveOrderMapper;
import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerCore.service.PizzaManManagerService;
import com.fcivillini.awesomePizzaManagerCore.validator.PizzaManManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.dto.EvolveOrderDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.PizzaRequestDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaManControllerTest {

    @Mock
    private EvolveOrderMapper evolveOrderMapper;

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
        Mockito.clearInvocations(evolveOrderMapper, orderRequestMapper, pizzaManManagerService, pizzaManManagerValidator);
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

    @Test
    @SneakyThrows
    void test_evolveStatus() {

        EvolveOrderDto evolveOrderDto = new EvolveOrderDto().setOrderId("id-1").setPizzaOrderId("pizza-1").setOrderStatus(OrderStatusDto.IN_PROGRESS);
        EvolveOrder evolveOrder = new EvolveOrder().setOrderId("id-1").setPizzaOrderId("pizza-1").setOrderStatus(OrderStatus.IN_PROGRESS);
        when(evolveOrderMapper.fromDto(evolveOrderDto)).thenReturn(evolveOrder);
        OrderRequest orderRequest = new OrderRequest()
                .setId("id-1")
                .setOrderStatus(OrderStatus.PENDING)
                .setPizzaList(asList(
                        new PizzaRequest().setId("pizza-1").setName("pizza-1").setOrderStatus(OrderStatus.PENDING)
                ));
        when(pizzaManManagerValidator.validateEvolveOrder(evolveOrder)).thenReturn(orderRequest);

        OrderRequest evolvedOrderRequest = new OrderRequest()
                .setId("id-1")
                .setOrderStatus(OrderStatus.IN_PROGRESS)
                .setPizzaList(asList(
                        new PizzaRequest().setId("pizza-1").setName("pizza-1").setOrderStatus(OrderStatus.IN_PROGRESS)
                ));
        when(pizzaManManagerService.evolveOrder(orderRequest, evolveOrder)).thenReturn(evolvedOrderRequest);
        OrderRequestDto evolvedOrderRequestDto = new OrderRequestDto()
                .setId("id-1")
                .setOrderStatus(OrderStatusDto.IN_PROGRESS)
                .setPizzaList(asList(
                        new PizzaRequestDto().setId("pizza-1").setName("pizza-1").setOrderStatus(OrderStatusDto.IN_PROGRESS)
                ));
        when(orderRequestMapper.toDto(evolvedOrderRequest)).thenReturn(evolvedOrderRequestDto);
        assertEquals(new ResponseEntity(evolvedOrderRequestDto, HttpStatus.OK), pizzaManController.evolveOrder(evolveOrderDto));
    }
}