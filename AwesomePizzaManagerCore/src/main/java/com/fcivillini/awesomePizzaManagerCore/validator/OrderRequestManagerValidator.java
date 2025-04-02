package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

/**
 * Validator interface for managing and validating order requests.
 */
public interface OrderRequestManagerValidator {

    /**
     * Validates and retrieves an order request by its ID.
     *
     * @param orderId the ID of the order request to retrieve
     * @return the validated order request
     * @throws PizzaException if the order request is not found or validation fails
     */
    OrderRequest validateGetOrderRequest(String orderId) throws PizzaException;

    /**
     * Validates the payment of an order request by its ID.
     *
     * @param orderId the ID of the order request to validate for payment
     * @throws PizzaException if the order request is not found or validation fails
     */
    void validatePayOrderRequest(String orderId) throws PizzaException;
}