package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface OrderRequestManagerService {
    String create(OrderRequest orderRequest);

    OrderRequest findOrder(String orderId) throws PizzaException;

    void payOrder(String orderId) throws PizzaException;
}
