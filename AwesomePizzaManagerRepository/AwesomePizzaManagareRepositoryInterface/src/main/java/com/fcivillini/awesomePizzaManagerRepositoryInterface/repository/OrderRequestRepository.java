package com.fcivillini.awesomePizzaManagerRepositoryInterface.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderRequestRepository {
    OrderRequestDao save(OrderRequestDao orderRequest);

    Optional<OrderRequestDao> findById(String orderId);
    Optional<OrderRequestDao> findFirstByCreationDate(final LocalDateTime startReservationTime, final LocalDateTime endReservationTime, final OrderStatusDao orderStatus);
}
