package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;

public interface OrderRequestManagerValidator {

    OrderRequestDao validateGetOrderRequest(String orderId) throws PizzaException;

    void validatePayOrderRequest(String orderId) throws PizzaException;
}
