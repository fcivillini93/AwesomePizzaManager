package com.fcivillini.awesomePizzaManagerCore.service;

import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

public interface PizzaManManagerService {

    OrderRequest findNewOrder() throws PizzaException;

    OrderRequest evolveOrder(OrderRequest orderRequest, EvolveOrder evolveOrder);
}
