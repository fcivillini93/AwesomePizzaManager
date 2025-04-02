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

import java.util.UUID;

/**
 * Implementation of the OrderRequestManagerService interface.
 * Provides methods to create, find, and pay for order requests.
 */
@Slf4j
@Setter
@Accessors(chain = true)
@Service
public class OrderRequestManagerServiceImpl implements OrderRequestManagerService {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    /**
     * Creates a new order request.
     *
     * @param orderRequest the order request to create
     * @return the ID of the created order request
     */
    @Override
    public String create(OrderRequest orderRequest) {
        log.info("start to create order: {}", orderRequest);
        orderRequest.getPizzaList().forEach(pizza -> pizza.setId(UUID.randomUUID().toString()));
        OrderRequest saved = orderRequestMapper.fromDao(orderRequestRepository.save(orderRequestMapper.toDao(orderRequest)));
        log.info("end to create order: id is [{}]", saved.getId());
        return saved.getId();
    }

    /**
     * Finds an order request by its ID.
     *
     * @param orderId the ID of the order request to find
     * @return the found order request
     * @throws PizzaException if the order request is not found
     */
    @Override
    public OrderRequest findOrder(String orderId) throws PizzaException {
        log.info("start to find order with id [{}]", orderId);
        OrderRequest result = orderRequestMapper.fromDao(orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new PizzaException(String.format("Order with id [%s] not found", orderId), HttpStatus.BAD_REQUEST)));
        log.info("end to create order with id [{}]", result.getId());
        return result;
    }

    /**
     * Marks an order request as paid.
     *
     * @param orderId the ID of the order request to mark as paid
     * @throws PizzaException if the order request is not found or cannot be marked as paid
     */
    @Override
    public void payOrder(String orderId) throws PizzaException {
        log.info("start to pay order with id [{}]", orderId);
        OrderRequest orderRequest = orderRequestMapper.fromDao(orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new PizzaException(String.format("Order with id [%s] not found", orderId), HttpStatus.BAD_REQUEST)));
        OrderRequest updateOrder = orderRequestMapper.fromDao(orderRequestRepository.save(orderRequestMapper.toDao(orderRequest.setOrderStatus(OrderStatus.FINISHED))));
        log.info("end to update order with id [{}] to status [{}]", updateOrder.getId(), updateOrder.getOrderStatus());
    }
}