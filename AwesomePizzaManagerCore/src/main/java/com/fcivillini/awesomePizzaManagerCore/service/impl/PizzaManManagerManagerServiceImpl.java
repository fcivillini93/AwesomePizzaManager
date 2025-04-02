package com.fcivillini.awesomePizzaManagerCore.service.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerCore.service.PizzaManManagerService;
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
import java.util.List;

/**
 * Implementation of the PizzaManManagerService interface.
 * Provides methods to manage and evolve pizza orders.
 */
@Slf4j
@Setter
@Accessors(chain = true)
@Service
public class PizzaManManagerManagerServiceImpl implements PizzaManManagerService {

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
        log.info("start to find new order");
        LocalDateTime now = LocalDateTime.now();
        OrderRequest result = orderRequestMapper.fromDao(orderRequestRepository.findFirstByCreationDate(getStartReservationTime(now), getEndReservationTime(now), OrderStatusDao.PENDING).orElseThrow(
                () -> new PizzaException("No new order found", HttpStatus.BAD_REQUEST)
        ));
        log.info("start to find order. Founded order [{}]", result.getId());
        return result;
    }

    /**
     * Evolves the status of an order request.
     *
     * @param orderRequest the order request to evolve
     * @param evolveOrder  the evolve order details
     * @return the evolved order request
     * @throws PizzaException if the order or pizza is not found, or if the status evolution is invalid
     */
    @Override
    public OrderRequest evolveOrder(OrderRequest orderRequest, EvolveOrder evolveOrder) throws PizzaException {
        log.info("start to evolve pizza [{}] in order [{}] to status [{}]", evolveOrder.getOrderId(), orderRequest.getId(), evolveOrder.getOrderStatus());
        PizzaRequest pizzaRequest = orderRequest.getPizzaList().stream().filter(p -> evolveOrder.getPizzaOrderId().equals(p.getId())).findAny()
                .orElseThrow(() -> new PizzaException(String.format("Unable to find pizza with id [%s]", evolveOrder.getPizzaOrderId()), HttpStatus.BAD_REQUEST));
        pizzaRequest.setOrderStatus(evolveOrder.getOrderStatus());
        orderRequest.setOrderStatus(evaluateOrderStatus(orderRequest.getPizzaList()));
        OrderRequest result = orderRequestMapper.fromDao(orderRequestRepository.save(orderRequestMapper.toDao(orderRequest)));
        log.info("end to evolve order [{}]. Final status is [{}]", result.getId(), result.getOrderStatus());
        return result;
    }

    /**
     * Evaluates the overall status of an order based on the status of its pizzas.
     *
     * @param pizzaList the list of pizzas in the order
     * @return the overall order status
     * @throws PizzaException if the order status combination is invalid
     */
    protected OrderStatus evaluateOrderStatus(List<PizzaRequest> pizzaList) throws PizzaException {
        if (pizzaList.stream().allMatch(p -> OrderStatus.PENDING.equals(p.getOrderStatus()))) {
            return OrderStatus.PENDING;
        } else if (pizzaList.stream().anyMatch(p -> OrderStatus.IN_PROGRESS.equals(p.getOrderStatus()))) {
            return OrderStatus.IN_PROGRESS;
        } else if (pizzaList.stream().allMatch(p -> p.getOrderStatus().equals(OrderStatus.READY))) {
            return OrderStatus.TO_PAY;
        }
        throw new PizzaException("Invalid order status combination", HttpStatus.BAD_REQUEST);
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