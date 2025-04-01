package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

public interface OrderRequestManagerService {
    String create(OrderRequest orderRequest);

    OrderRequest findOrder(String orderId) throws PizzaException;
}
