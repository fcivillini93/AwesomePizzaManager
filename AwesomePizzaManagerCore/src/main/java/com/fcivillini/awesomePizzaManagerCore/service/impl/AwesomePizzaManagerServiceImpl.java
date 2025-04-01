package com.fcivillini.awesomePizzaManagerCore.service.impl;

import com.fcivillini.awesomePizzaManagerCore.mapper.OrderRequestMapper;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.service.AwesomePizzaManagerService;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Setter
@Accessors(chain = true)
@Service
public class AwesomePizzaManagerServiceImpl implements AwesomePizzaManagerService {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Override
    public String create(OrderRequest orderRequest) {
        log.info("start to create order: {}", orderRequest);
        OrderRequest saved = orderRequestMapper.fromDao(orderRequestRepository.save(orderRequestMapper.toDao(orderRequest)));
        log.info("end to create order: {}", orderRequest);
        return saved.getId();
    }
}
