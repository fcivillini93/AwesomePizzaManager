package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

public interface OrderRequestManagerValidator {

    OrderRequest validateGetOrderRequest(String orderId) throws PizzaException;

    void validatePayOrderRequest(String orderId) throws PizzaException;
}
