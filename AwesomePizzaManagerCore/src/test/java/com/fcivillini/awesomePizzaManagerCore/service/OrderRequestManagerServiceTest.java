package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.service.impl.OrderRequestManagerServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRequestManagerServiceTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;

    @Mock
    private OrderRequestRepository orderRequestRepository;

    @InjectMocks
    private OrderRequestManagerServiceImpl awesomePizzaManagerService;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRequestMapper, orderRequestRepository);
    }

    @Test
    void test_create() {

        OrderRequest orderQuest = new OrderRequest().setUserName("user");
        OrderRequestDao toSaveDao = new OrderRequestDao().setUserName("user");
        OrderRequestDao savedDao = new OrderRequestDao().setId("id-1").setUserName("user");
        when(orderRequestMapper.toDao(orderQuest)).thenReturn(toSaveDao);
        when(orderRequestRepository.save(toSaveDao)).thenReturn(savedDao);
        when(orderRequestMapper.fromDao(savedDao)).thenReturn(new OrderRequest().setId("id-1").setUserName("user"));
        assertEquals("id-1", awesomePizzaManagerService.create(orderQuest));

    }

    @Test
    @SneakyThrows
    void test_findOrder() {
        OrderRequest orderRequest = new OrderRequest().setId("order-id");
        OrderRequestDao orderRequestDao = new OrderRequestDao().setId("order-id");

        when(orderRequestRepository.findById("order-id")).thenReturn(java.util.Optional.of(orderRequestDao));
        when(orderRequestMapper.fromDao(orderRequestDao)).thenReturn(orderRequest);
        assertEquals(orderRequest, awesomePizzaManagerService.findOrder("order-id"));
    }

    @Test
    void test_payOrder() {
        OrderRequestDao toPayOrderDao = new OrderRequestDao().setId("order-id").setOrderStatus(OrderStatusDao.TO_PAY);
        OrderRequest toPayOrder = new OrderRequest().setId("order-id").setOrderStatus(OrderStatus.TO_PAY);

        OrderRequestDao payedOrderDao = new OrderRequestDao().setId("order-id").setOrderStatus(OrderStatusDao.FINISHED);
        OrderRequest payedOrder = new OrderRequest().setId("order-id").setOrderStatus(OrderStatus.FINISHED);

        when(orderRequestRepository.findById("order-id")).thenReturn(java.util.Optional.of(toPayOrderDao));
        when(orderRequestMapper.fromDao(toPayOrderDao)).thenReturn(toPayOrder);

        when(orderRequestMapper.toDao(payedOrder)).thenReturn(payedOrderDao);
        when(orderRequestRepository.save(payedOrderDao)).thenReturn(payedOrderDao);
        when(orderRequestMapper.fromDao(payedOrderDao)).thenReturn(payedOrder);

        assertDoesNotThrow(()->awesomePizzaManagerService.payOrder("order-id"));

    }
}