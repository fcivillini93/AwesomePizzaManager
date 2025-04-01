package com.fcivillini.awesomePizzaManagerRepositoryMongo.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PizzaRequestMongo {

    private String id;

    private String name;

    private OrderStatusMongo orderStatus;
}