package com.fcivillini.awesomePizzaManagerCore.validator;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.dto.EvolveOrderDto;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import jakarta.validation.Valid;

public interface PizzaManManagerValidator {

    OrderRequest validateEvolveOrder() throws PizzaException;

    OrderRequest validateEvolveOrder(@Valid EvolveOrderDto evolveOrderDto);
}
