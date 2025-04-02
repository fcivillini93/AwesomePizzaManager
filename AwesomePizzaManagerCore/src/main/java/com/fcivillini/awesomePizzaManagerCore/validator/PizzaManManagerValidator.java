package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

/**
 * Validator interface for managing and validating pizza orders.
 */
public interface PizzaManManagerValidator {

    /**
     * Finds a new order request.
     *
     * @return the found order request
     * @throws PizzaException if no new order is found
     */
    OrderRequest findNewOrder() throws PizzaException;

    /**
     * Validates the evolution of an order request.
     *
     * @param evolveOrderDto the evolve order DTO
     * @return the validated order request
     * @throws PizzaException if the order or pizza is not found, or if the status evolution is invalid
     */
    OrderRequest validateEvolveOrder(EvolveOrder evolveOrderDto) throws PizzaException;
}