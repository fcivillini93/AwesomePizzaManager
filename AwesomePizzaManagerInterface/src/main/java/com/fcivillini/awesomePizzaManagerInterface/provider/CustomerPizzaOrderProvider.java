package com.fcivillini.awesomePizzaManagerInterface.provider;

import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer Pizza Order", description = "API for managing customer pizza orders")
@RequestMapping("api/v1/customer")
public interface CustomerPizzaOrderProvider {

    @Operation(summary = "Create a new order", description = "Creates a new pizza order for the customer")
    @PutMapping("/createOrder")
    OrderRequestDto createOrder(@RequestBody OrderRequestDto orderRequestDto);

    @Operation(summary = "Get order details", description = "Retrieves the details of an order by its ID")
    @GetMapping("/getOrder/{orderId}")
    OrderRequestDto getOrder(@Parameter(description = "ID of the order to be retrieved") @PathVariable("orderId") String orderId);

    @Operation(summary = "Pay for an order", description = "Marks an order as paid by its ID")
    @PutMapping("/payOrder/{orderId}")
    void payOrder(@Parameter(description = "ID of the order to be paid") @PathVariable("orderId") String orderId);
}