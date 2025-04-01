package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

public interface OrderRequestManagerValidator {

    void validateGetOrderRequest(String orderId) throws PizzaException;
}
