package com.fcivillini.awesomePizzaManagerRepositoryInterface.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository interface for managing order requests.
 */
public interface OrderRequestRepository {

    /**
     * Saves an order request.
     *
     * @param orderRequest the order request to save
     * @return the saved order request
     */
    OrderRequestDao save(OrderRequestDao orderRequest);

    /**
     * Finds an order request by its ID.
     *
     * @param orderId the ID of the order request to find
     * @return an Optional containing the found order request, or empty if not found
     */
    Optional<OrderRequestDao> findById(String orderId);

    /**
     * Finds the first order request by creation date within a specified time interval and status.
     *
     * @param startReservationTime the start of the reservation time interval
     * @param endReservationTime   the end of the reservation time interval
     * @param orderStatus          the status of the order request to find
     * @return an Optional containing the found order request, or empty if not found
     */
    Optional<OrderRequestDao> findFirstByCreationDate(final LocalDateTime startReservationTime, final LocalDateTime endReservationTime, final OrderStatusDao orderStatus);
}