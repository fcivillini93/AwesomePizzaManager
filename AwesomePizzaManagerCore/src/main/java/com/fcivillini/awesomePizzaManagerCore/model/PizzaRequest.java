package com.fcivillini.awesomePizzaManagerCore.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PizzaRequest {

    private String id;

    private String name;

    private OrderStatus orderStatus;
}