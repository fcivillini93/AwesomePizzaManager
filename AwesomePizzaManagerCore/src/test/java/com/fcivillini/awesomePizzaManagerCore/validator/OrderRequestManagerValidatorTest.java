package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.validator.impl.OrderRequestManagerValidatorImpl;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRequestManagerValidatorTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;
    @Mock
    private OrderRequestRepository orderRequestRepository;

    @InjectMocks
    private OrderRequestManagerValidatorImpl orderRequestManagerValidator;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRequestRepository, orderRequestMapper);
    }

    @Test
    void test_validateGetOrderRequest_ko() {

        try {
            when(orderRequestRepository.findById("id-1")).thenReturn(Optional.empty());
            orderRequestManagerValidator.validateGetOrderRequest("id-1");
            fail("should throw exception");
        } catch (PizzaException e) {
            assertEquals("Order whit id [id-1] not found", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
        }

    }

    @Test
    void test_validateGetOrderRequest_ok() {
        OrderRequest orderRequest = new OrderRequest().setId("id-1");
        OrderRequestDao orderRequestDao = new OrderRequestDao().setId("id-1");
        when(orderRequestRepository.findById("id-1")).thenReturn(Optional.of(orderRequestDao));
        when(orderRequestMapper.fromDao(orderRequestDao)).thenReturn(orderRequest);
        assertDoesNotThrow(() -> orderRequestManagerValidator.validateGetOrderRequest("id-1"));

    }

    @Test
    void test_validatePayOrderRequest_ko() {

        try {
            OrderRequest orderRequest = new OrderRequest().setId("id-1").setOrderStatus(OrderStatus.PENDING);
            OrderRequestDao orderRequestDao = new OrderRequestDao().setId("id-1").setOrderStatus(OrderStatusDao.PENDING);
            when(orderRequestRepository.findById("id-1")).thenReturn(Optional.of(orderRequestDao));
            when(orderRequestMapper.fromDao(orderRequestDao)).thenReturn(orderRequest);
            orderRequestManagerValidator.validatePayOrderRequest("id-1");
            fail("should throw exception");
        } catch (PizzaException e) {
            assertEquals("Order whit id [id-1] is not in [TO_PAY] status", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
        }
    }

    @Test
    void test_validatePayOrderRequest_ok() {
        OrderRequest orderRequest = new OrderRequest().setId("id-1").setOrderStatus(OrderStatus.TO_PAY);
        OrderRequestDao orderRequestDao = new OrderRequestDao().setId("id-1").setOrderStatus(OrderStatusDao.TO_PAY);
        when(orderRequestRepository.findById("id-1")).thenReturn(Optional.of(orderRequestDao));
        when(orderRequestMapper.fromDao(orderRequestDao)).thenReturn(orderRequest);
        assertDoesNotThrow(() -> orderRequestManagerValidator.validatePayOrderRequest("id-1"));

    }
}