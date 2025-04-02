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

    protected LocalDateTime getEndReservationTime(LocalDateTime now) {
        return now.plusMinutes(intervalMinutes);
    }

    protected LocalDateTime getStartReservationTime(LocalDateTime now) {
        return now.minusMinutes(intervalMinutes);
    }


}
