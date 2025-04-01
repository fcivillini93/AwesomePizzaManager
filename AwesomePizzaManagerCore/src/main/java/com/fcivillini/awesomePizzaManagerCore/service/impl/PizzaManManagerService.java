package com.fcivillini.awesomePizzaManagerCore.service.impl;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

public interface PizzaManManagerService {

    OrderRequest findNewOrder() throws PizzaException;

}
