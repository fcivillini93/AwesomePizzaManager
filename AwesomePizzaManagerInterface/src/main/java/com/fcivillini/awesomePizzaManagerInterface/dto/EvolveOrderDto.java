package com.fcivillini.awesomePizzaManagerInterface.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "DTO for evolving an order")
public class EvolveOrderDto {

    @NotNull(message = "orderId is required")
    @NotEmpty(message = "orderId is required")
    @Schema(description = "ID of the order", example = "12345")
    private String orderId;

    @NotNull(message = "pizzaOrderId is required")
    @NotEmpty(message = "pizzaOrderId is required")
    @Schema(description = "ID of the pizza order", example = "67890")
    private String pizzaOrderId;

    @NotNull(message = "orderStatus is required")
    @NotEmpty(message = "orderStatus is required")
    @Schema(description = "Status of the order", example = "PENDING")
    private OrderStatusDto orderStatus;
}
