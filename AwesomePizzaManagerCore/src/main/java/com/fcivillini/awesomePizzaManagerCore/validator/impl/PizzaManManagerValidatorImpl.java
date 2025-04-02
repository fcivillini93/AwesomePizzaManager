package com.fcivillini.awesomePizzaManagerCore.validator.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerCore.validator.PizzaManManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementation of the PizzaManManagerValidator interface.
 * Provides methods to validate and find order requests.
 */
@Slf4j
@Setter
@Accessors(chain = true)
@Service
public class PizzaManManagerValidatorImpl implements PizzaManManagerValidator {

    @Value("${awesomePizzaManager.intervalMinutes}")
    private Integer intervalMinutes;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    /**
     * Finds a new order request within the reservation time interval.
     *
     * @return the found order request
     * @throws PizzaException if no new order is found
     */
    @Override
    public OrderRequest findNewOrder() throws PizzaException {
        LocalDateTime now = LocalDateTime.now();
        return orderRequestMapper.fromDao(orderRequestRepository.findFirstByCreationDate(getStartReservationTime(now), getEndReservationTime(now), OrderStatusDao.PENDING).orElseThrow(
                () -> new PizzaException("No new order found", HttpStatus.BAD_REQUEST)
        ));
    }

    /**
     * Validates the evolution of an order request.
     *
     * @param evolveOrderDto the evolve order DTO
     * @return the validated order request
     * @throws PizzaException if the order or pizza is not found, or if the status evolution is invalid
     */
    @Override
    public OrderRequest validateEvolveOrder(EvolveOrder evolveOrderDto) throws PizzaException {
        OrderRequest orderRequest = orderRequestMapper.fromDao(orderRequestRepository.findById(evolveOrderDto.getOrderId()).orElseThrow(
                () -> new PizzaException(String.format("Unable to find order with id [%s]", evolveOrderDto.getOrderId()), HttpStatus.BAD_REQUEST)
        ));
        PizzaRequest pizzaRequest = orderRequest.getPizzaList().stream().filter(p -> evolveOrderDto.getPizzaOrderId().equals(p.getId())).findAny()
                .orElseThrow(
                        () -> new PizzaException(String.format("Unable to find pizza with id [%s]", evolveOrderDto.getPizzaOrderId()), HttpStatus.BAD_REQUEST)
                );

        if (pizzaRequest.getOrderStatus() != getOrderStatusLevel(evolveOrderDto.getOrderStatus())) {
            throw new PizzaException(String.format("Unable to evolve pizza with id [%s] to status [%s]", evolveOrderDto.getPizzaOrderId(), evolveOrderDto.getOrderStatus()), HttpStatus.BAD_REQUEST);
        }

        return orderRequest;
    }

    /**
     * Gets the previous order status level for validation.
     *
     * @param orderStatus the current order status
     * @return the previous order status level
     * @throws PizzaException if the order status is invalid
     */
    protected OrderStatus getOrderStatusLevel(OrderStatus orderStatus) throws PizzaException {
        return switch (orderStatus) {
            case IN_PROGRESS -> OrderStatus.PENDING;
            case READY -> OrderStatus.IN_PROGRESS;
            case TO_PAY -> OrderStatus.READY;
            default -> throw new PizzaException("Invalid order status", HttpStatus.BAD_REQUEST);
        };
    }

    /**
     * Gets the end reservation time based on the current time and interval.
     *
     * @param now the current time
     * @return the end reservation time
     */
    protected LocalDateTime getEndReservationTime(LocalDateTime now) {
        return now.plusMinutes(intervalMinutes);
    }

    /**
     * Gets the start reservation time based on the current time and interval.
     *
     * @param now the current time
     * @return the start reservation time
     */
    protected LocalDateTime getStartReservationTime(LocalDateTime now) {
        return now.minusMinutes(intervalMinutes);
    }
}