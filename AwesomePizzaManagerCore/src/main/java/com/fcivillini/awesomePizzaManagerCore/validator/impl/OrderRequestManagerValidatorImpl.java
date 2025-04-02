package com.fcivillini.awesomePizzaManagerCore.validator.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.validator.OrderRequestManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
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
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Override
    public OrderRequest validateGetOrderRequest(String orderId) throws PizzaException {

        return orderRequestMapper.fromDao(orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new PizzaException(String.format("Order whit id [%s] not found", orderId), HttpStatus.BAD_REQUEST)));
    }

    @Override
    public void validatePayOrderRequest(String orderId) throws PizzaException {
        OrderRequest orderRequest = this.validateGetOrderRequest(orderId);
        if (!OrderStatus.TO_PAY.equals(orderRequest.getOrderStatus())) {
            throw new PizzaException(String.format("Order whit id [%s] is not in [%s] status", orderId, OrderStatusDao.TO_PAY), HttpStatus.BAD_REQUEST);
        }
    }
}
