package com.fcivillini.awesomePizzaManagerRepositoryInterface.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PizzaRequestDao {

    private String id;

    private String name;

    private OrderStatusDao orderStatus;
}