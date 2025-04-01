package com.fcivillini.awesomePizzaManagerRepositoryMongo.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper.OrderRequestMongoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class OrderRequestRepositoryMongo implements OrderRequestRepository {

    @Autowired
    private OrderRequestRepositorySpringMongo springRepository;

    @Autowired
    private OrderRequestMongoMapper mapper;

    @Override
    public OrderRequestDao save(OrderRequestDao orderRequest) {
        if (orderRequest == null) {
            log.warn("OrderRequestDao is null");
            return null;
        }
        return mapper.toDao(springRepository.save(mapper.fromDao(orderRequest)));
    }

    @Override
    public Optional<OrderRequestDao> findById(String orderId) {
        if (StringUtils.isAllBlank(orderId)) {
            log.warn("orderId is null");
            return Optional.empty();
        }
        return springRepository.findById(orderId)
                .map(order -> mapper.toDao(order));
    }

}
