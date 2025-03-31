package com.fcivillini.awesomePizzaManagerInterface.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Pizza request data transfer object")
public class PizzaRequestDto {

    @Schema(description = "Pizza name", example = "Margherita")
    private String name;

    @Schema(description = "Order status of the pizza")
    private OrderStatusDto orderStatusDto;
}