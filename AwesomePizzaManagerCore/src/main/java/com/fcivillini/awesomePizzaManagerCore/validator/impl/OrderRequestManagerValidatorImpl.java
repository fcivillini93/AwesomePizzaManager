package com.fcivillini.awesomePizzaManagerCore.validator.impl;

import com.fcivillini.awesomePizzaManagerCore.validator.OrderRequestManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Setter
@Accessors(chain = true)
@Service
public class OrderRequestManagerValidatorImpl implements OrderRequestManagerValidator {

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Override
    public OrderRequestDao validateGetOrderRequest(String orderId) throws PizzaException {

        return orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new PizzaException(String.format("Order whit id [%s] not found", orderId), HttpStatus.BAD_REQUEST));
    }

    @Override
    public void validatePayOrderRequest(String orderId) throws PizzaException {
        OrderRequestDao orderRequestDao = this.validateGetOrderRequest(orderId);
        if (!OrderStatusDao.TO_PAY.equals(orderRequestDao.getOrderStatus())) {
            throw new PizzaException(String.format("Order whit id [%s] is not in [%s] status", orderId, OrderStatusDao.TO_PAY), HttpStatus.BAD_REQUEST);
        }
    }
}
