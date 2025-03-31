package com.fcivillini.awesomePizzaManagerCore.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Accessors(chain = true)
public class OrderRequest {

    private String id;

    private String userName;

    private OrderStatus orderStatus;

    private String phoneNumber;

    private Collection<PizzaRequest> pizzaList;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}