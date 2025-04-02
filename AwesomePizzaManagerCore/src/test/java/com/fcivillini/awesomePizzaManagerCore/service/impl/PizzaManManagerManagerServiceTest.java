package com.fcivillini.awesomePizzaManagerCore.service.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}