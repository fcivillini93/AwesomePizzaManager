package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;

public interface PizzaManManagerValidator {

    OrderRequest findNewOrder() throws PizzaException;

    OrderRequest validateEvolveOrder(EvolveOrder evolveOrderDto) throws PizzaException;
}
