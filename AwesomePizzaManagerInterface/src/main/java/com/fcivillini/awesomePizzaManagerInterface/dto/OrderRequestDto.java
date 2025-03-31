package com.fcivillini.awesomePizzaManagerInterface.dto;

    import io.swagger.v3.oas.annotations.media.Schema;
    import lombok.Data;
    import lombok.experimental.Accessors;

    import java.time.LocalDateTime;
    import java.util.Collection;

    @Data
    @Accessors(chain = true)
    @Schema(description = "Order request data transfer object")
    public class OrderRequestDto {

        @Schema(description = "Order ID", example = "12345")
        private String id;

        @Schema(description = "User name", example = "John Doe")
        private String userName;

        @Schema(description = "Order status")
        private OrderStatusDto orderStatusDto;

        @Schema(description = "List of pizzas in the order")
        private Collection<PizzaRequestDto> pizzaList;

        @Schema(description = "Order creation date and time")
        private LocalDateTime creationDate;

        @Schema(description = "Order update date and time")
        private LocalDateTime updateDate;
    }