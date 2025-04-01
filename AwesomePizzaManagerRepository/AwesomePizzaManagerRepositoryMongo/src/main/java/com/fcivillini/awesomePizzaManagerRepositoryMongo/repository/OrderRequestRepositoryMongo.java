package com.fcivillini.awesomePizzaManagerRepositoryMongo.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.repository.OrderRequestRepository;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper.OrderRequestMongoMapper;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper.OrderStatusMongoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class OrderRequestRepositoryMongo implements OrderRequestRepository {

    @Autowired
    private OrderRequestRepositorySpringMongo springRepository;

    @Autowired
    private OrderRequestMongoMapper orderRequestMongoMapper;

    @Autowired
    private OrderStatusMongoMapper orderStatusMongoMapper;

    @Override
    public OrderRequestDao save(OrderRequestDao orderRequest) {
        if (orderRequest == null) {
            log.warn("OrderRequestDao is null");
            return null;
        }
        return orderRequestMongoMapper.toDao(springRepository.save(orderRequestMongoMapper.fromDao(orderRequest)));
    }

    @Override
    public Optional<OrderRequestDao> findById(String orderId) {
        if (StringUtils.isAllBlank(orderId)) {
            log.warn("orderId is null");
            return Optional.empty();
        }
        return springRepository.findById(orderId)
                .map(order -> orderRequestMongoMapper.toDao(order));
    }

    @Override
    public Optional<OrderRequestDao> findFirstByCreationDate(final LocalDateTime startReservationTime, final LocalDateTime endReservationTime, final OrderStatusDao orderStatus) {

        List<OrderRequestDao> list = springRepository.findByReservationTimeBetweenAndOrderStatusOrderByCreationDateAsc(
                        orderRequestMongoMapper.toDate(startReservationTime),
                        orderRequestMongoMapper.toDate(endReservationTime),
                        orderStatusMongoMapper.fromDao(orderStatus)
                ).stream()
                .map(order -> orderRequestMongoMapper.toDao(order))
                .sorted(Comparator.comparing(OrderRequestDao::getCreationDate))
                .toList();
        return list.stream().findFirst();

    }

}
