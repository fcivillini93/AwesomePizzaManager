package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

/**
 * Service interface for managing order requests.
 */
public interface OrderRequestManagerService {

    /**
     * Creates a new order request.
     *
     * @param orderRequest the order request to create
     * @return the ID of the created order request
     */
    String create(OrderRequest orderRequest);

    /**
     * Finds an order request by its ID.
     *
     * @param orderId the ID of the order request to find
     * @return the found order request
     * @throws PizzaException if the order request is not found
     */
    OrderRequest findOrder(String orderId) throws PizzaException;

    /**
     * Marks an order request as paid.
     *
     * @param orderId the ID of the order request to mark as paid
     * @throws PizzaException if the order request is not found or cannot be marked as paid
     */
    void payOrder(String orderId) throws PizzaException;
}