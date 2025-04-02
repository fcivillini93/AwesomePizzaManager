package com.fcivillini.awesomePizzaManagerCore.validator.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaManManagerValidatorTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;
    @Mock
    private OrderRequestRepository orderRequestRepository;

    @InjectMocks
    private PizzaManManagerValidatorImpl pizzaManManagerValidator;

    @BeforeEach
    void setUp() {
        pizzaManManagerValidator.setIntervalMinutes(15);
        Mockito.clearInvocations(orderRequestRepository, orderRequestMapper);
    }

    @Test
    void test_getStartReservationTime() {
        assertEquals(LocalDateTime.of(2021, 1, 1, 0, 15), pizzaManManagerValidator.getStartReservationTime(LocalDateTime.of(2021, 1, 1, 0, 30)));
    }

    @Test
    void test_getEndReservationTime() {
        assertEquals(LocalDateTime.of(2021, 1, 1, 0, 45), pizzaManManagerValidator.getEndReservationTime(LocalDateTime.of(2021, 1, 1, 0, 30)));
    }

    @Test
    @SneakyThrows
    void test_findNewOrder() {

        try {
            when(orderRequestRepository.findFirstByCreationDate(any(LocalDateTime.class), any(LocalDateTime.class), any(OrderStatusDao.class))).thenReturn(Optional.empty());
            pizzaManManagerValidator.validateEvolveOrder();
            fail("should throw exception");
        } catch (PizzaException e) {
            assertEquals("No new order found", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
        }

        OrderRequestDao orderRequestDao = new OrderRequestDao().setId("id-1");
        OrderRequest orderRequest = new OrderRequest().setId("id-1");
        when(orderRequestRepository.findFirstByCreationDate(any(LocalDateTime.class), any(LocalDateTime.class), any(OrderStatusDao.class))).thenReturn(Optional.of(orderRequestDao));
        when(orderRequestMapper.fromDao(orderRequestDao)).thenReturn(orderRequest);
        assertEquals(orderRequest, pizzaManManagerValidator.validateEvolveOrder());
    }
}