package com.fcivillini.awesomePizzaManagerInterface.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Order status enumeration")
public enum OrderStatusDto {
    @Schema(description = "Order is pending")
    PENDING,

    @Schema(description = "Order is in progress")
    IN_PROGRESS,

    @Schema(description = "Order is ready")
    READY,

    @Schema(description = "Order is to be paid")
    TO_PAY,

    @Schema(description = "Order is finished")
    FINISHED,

    @Schema(description = "Order is canceled")
    CANCELED
}