package com.fcivillini.awesomePizzaManagerRepositoryInterface.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;

import java.util.Optional;

public interface OrderRequestRepository {
    OrderRequestDao save(OrderRequestDao orderRequest);

    Optional<OrderRequestDao> findById(String orderId);
}
