package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.validator.impl.OrderRequestManagerValidatorImpl;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
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
    private OrderRequestRepository orderRequestRepository;

    @InjectMocks
    private OrderRequestManagerValidatorImpl orderRequestManagerValidator;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRequestRepository);
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

        when(orderRequestRepository.findById("id-1")).thenReturn(Optional.of(new OrderRequestDao()));
        assertDoesNotThrow(() -> orderRequestManagerValidator.validateGetOrderRequest("id-1"));

    }
}