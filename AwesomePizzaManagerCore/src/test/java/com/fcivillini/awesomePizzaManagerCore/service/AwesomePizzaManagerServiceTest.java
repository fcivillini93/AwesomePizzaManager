package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.service.impl.AwesomePizzaManagerServiceImpl;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AwesomePizzaManagerServiceTest {

    @Mock
    private OrderRequestMapper orderRequestMapper;

    @Mock
    private OrderRequestRepository orderRequestRepository;

    @InjectMocks
    private AwesomePizzaManagerServiceImpl awesomePizzaManagerService;

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
}