package com.fcivillini.awesomePizzaManagerCore.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EvolveOrder {

    private String orderId;

    private String pizzaOrderId;

    private OrderStatus orderStatus;
}
