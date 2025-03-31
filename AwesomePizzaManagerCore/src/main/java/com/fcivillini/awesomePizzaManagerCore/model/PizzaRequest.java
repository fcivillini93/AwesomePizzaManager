package com.fcivillini.awesomePizzaManagerCore.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PizzaRequest {

    private String name;

    private OrderStatus orderStatus;
}