package com.fcivillini.awesomePizzaManagerCore.service.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.service.OrderRequestManagerService;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
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
public class OrderRequestManagerServiceImpl implements OrderRequestManagerService {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Override
    public String create(OrderRequest orderRequest) {
        log.info("start to create order: {}", orderRequest);
        OrderRequest saved = orderRequestMapper.fromDao(orderRequestRepository.save(orderRequestMapper.toDao(orderRequest)));
        log.info("end to create order: id is [{}]", saved.getId());
        return saved.getId();
    }

    @Override
    public OrderRequest findOrder(String orderId) throws PizzaException {
        log.info("start to find order with id [{}]", orderId);
        OrderRequest result = orderRequestMapper.fromDao(orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new PizzaException(String.format("Order whit id [%s] not found", orderId), HttpStatus.BAD_REQUEST)));
        log.info("end to create order with id [{}]", result.getId());
        return result;
    }

    @Override
    public void payOrder(String orderId) throws PizzaException {
        log.info("start to pay order with id [{}]", orderId);
        OrderRequest orderRequest = orderRequestMapper.fromDao(orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new PizzaException(String.format("Order whit id [%s] not found", orderId), HttpStatus.BAD_REQUEST)));
        OrderRequest updateOrder = orderRequestMapper.fromDao(orderRequestRepository.save(orderRequestMapper.toDao(orderRequest.setOrderStatus(OrderStatus.FINISHED))));
        log.info("end to update order with id [{}] to status [{}]", updateOrder.getId(), updateOrder.getOrderStatus());
    }
}
