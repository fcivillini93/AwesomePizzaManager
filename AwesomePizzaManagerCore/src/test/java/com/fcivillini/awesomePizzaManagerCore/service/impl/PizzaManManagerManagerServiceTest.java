package com.fcivillini.awesomePizzaManagerCore.service.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.PizzaRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaManManagerManagerServiceTest {

    @Mock
    private OrderRequestRepository orderRequestRepository;

    @Mock
    private OrderRequestMapper orderRequestMapper;

    @InjectMocks
    private PizzaManManagerManagerServiceImpl pizzaManManagerManagerService;

    @BeforeEach
    void setUp() {
        pizzaManManagerManagerService.setIntervalMinutes(15);
        Mockito.clearInvocations(orderRequestMapper, orderRequestRepository);
    }

    @Test
    void test_getStartReservationTime() {
        assertEquals(LocalDateTime.of(2021, 1, 1, 0, 15), pizzaManManagerManagerService.getStartReservationTime(LocalDateTime.of(2021, 1, 1, 0, 30)));
    }

    @Test
    void test_getEndReservationTime() {
        assertEquals(LocalDateTime.of(2021, 1, 1, 0, 45), pizzaManManagerManagerService.getEndReservationTime(LocalDateTime.of(2021, 1, 1, 0, 30)));
    }

    @Test
    @SneakyThrows
    void test_findNewOrder() {
        OrderRequestDao orderRequestDao = new OrderRequestDao().setId("id-1");
        OrderRequest orderRequest = new OrderRequest().setId("id-1");
        when(orderRequestRepository.findFirstByCreationDate(any(LocalDateTime.class), any(LocalDateTime.class), any(OrderStatusDao.class))).thenReturn(Optional.of(orderRequestDao));
        when(orderRequestMapper.fromDao(orderRequestDao)).thenReturn(orderRequest);
        assertEquals(orderRequest, pizzaManManagerManagerService.findNewOrder());
    }

    @Test
    @SneakyThrows
    void test_evaluateOrderStatus() {

        try {
            pizzaManManagerManagerService.evaluateOrderStatus(List.of(
                    new PizzaRequest().setOrderStatus(OrderStatus.PENDING),
                    new PizzaRequest().setOrderStatus(OrderStatus.READY)));
            fail("should throw exception");
        } catch (PizzaException exception) {
            assertEquals("Invalid order status combination", exception.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        }
        assertEquals(OrderStatus.PENDING, pizzaManManagerManagerService.evaluateOrderStatus(List.of(new PizzaRequest().setOrderStatus(OrderStatus.PENDING))));
        assertEquals(OrderStatus.IN_PROGRESS, pizzaManManagerManagerService.evaluateOrderStatus(List.of(new PizzaRequest().setOrderStatus(OrderStatus.PENDING), new PizzaRequest().setOrderStatus(OrderStatus.IN_PROGRESS))));
        assertEquals(OrderStatus.TO_PAY, pizzaManManagerManagerService.evaluateOrderStatus(List.of(new PizzaRequest().setOrderStatus(OrderStatus.READY))));

    }

    @Test
    @SneakyThrows
    void test_evolveOrder() {
        EvolveOrder evolveOrder = new EvolveOrder().setOrderId("id-1").setOrderStatus(OrderStatus.READY).setPizzaOrderId("pizza-1");
        OrderRequest orderRequest = new OrderRequest()
                .setId("id-1")
                .setOrderStatus(OrderStatus.IN_PROGRESS)
                .setPizzaList(asList(
                        new PizzaRequest().setId("pizza-1").setName("margherita").setOrderStatus(OrderStatus.IN_PROGRESS)
                ));

        OrderRequest evolvedOrderRequest = new OrderRequest()
                .setId("id-1")
                .setOrderStatus(OrderStatus.TO_PAY)
                .setPizzaList(asList(
                        new PizzaRequest().setId("pizza-1").setName("margherita").setOrderStatus(OrderStatus.READY)
                ));

        OrderRequestDao evolvedOrderRequestDao = new OrderRequestDao()
                .setId("id-1")
                .setOrderStatus(OrderStatusDao.TO_PAY)
                .setPizzaList(asList(
                        new PizzaRequestDao().setId("pizza-1").setName("margherita").setOrderStatus(OrderStatusDao.READY)
                ));
        when(orderRequestMapper.toDao(evolvedOrderRequest)).thenReturn(evolvedOrderRequestDao);
        when(orderRequestRepository.save(evolvedOrderRequestDao)).thenReturn(evolvedOrderRequestDao);
        when(orderRequestMapper.fromDao(evolvedOrderRequestDao)).thenReturn(evolvedOrderRequest);
        assertEquals(evolvedOrderRequest, pizzaManManagerManagerService.evolveOrder(orderRequest, evolveOrder));
    }
}