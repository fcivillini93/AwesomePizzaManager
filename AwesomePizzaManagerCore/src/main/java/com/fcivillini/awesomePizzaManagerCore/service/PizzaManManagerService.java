package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

/**
 * Service interface for managing pizza orders.
 */
public interface PizzaManManagerService {

    /**
     * Finds a new order request.
     *
     * @return the found order request
     * @throws PizzaException if no new order is found
     */
    OrderRequest findNewOrder() throws PizzaException;

    /**
     * Evolves the status of an order request.
     *
     * @param orderRequest the order request to evolve
     * @param evolveOrder  the evolve order details
     * @return the evolved order request
     * @throws PizzaException if the order or pizza is not found, or if the status evolution is invalid
     */
    OrderRequest evolveOrder(OrderRequest orderRequest, EvolveOrder evolveOrder) throws PizzaException;
}