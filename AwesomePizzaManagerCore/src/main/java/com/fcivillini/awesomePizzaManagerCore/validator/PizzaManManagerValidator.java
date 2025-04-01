package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;

public interface PizzaManManagerValidator {

    OrderRequestDao validateFindNewOrder() throws PizzaException;

}
