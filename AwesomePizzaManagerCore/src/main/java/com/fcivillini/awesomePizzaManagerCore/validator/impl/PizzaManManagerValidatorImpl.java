package com.fcivillini.awesomePizzaManagerCore.validator.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.validator.PizzaManManagerValidator;
import com.fcivillini.awesomePizzaManagerInterface.dto.EvolveOrderDto;
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

    @Override
    public OrderRequest validateEvolveOrder() throws PizzaException {
        LocalDateTime now = LocalDateTime.now();
        return orderRequestMapper.fromDao(orderRequestRepository.findFirstByCreationDate(getStartReservationTime(now), getEndReservationTime(now), OrderStatusDao.PENDING).orElseThrow(
                () -> new PizzaException("No new order found", HttpStatus.BAD_REQUEST)
        ));
    }

    @Override
    public OrderRequest validateEvolveOrder(EvolveOrderDto evolveOrderDto) {
        return null;
    }

    protected LocalDateTime getEndReservationTime(LocalDateTime now) {
        return now.plusMinutes(intervalMinutes);
    }

    protected LocalDateTime getStartReservationTime(LocalDateTime now) {
        return now.minusMinutes(intervalMinutes);
    }


}
