package com.fcivillini.awesomePizzaManagerRepositoryInterface.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;

public interface OrderRequestRepository {
    OrderRequestDao save(OrderRequestDao orderRequest);
}
