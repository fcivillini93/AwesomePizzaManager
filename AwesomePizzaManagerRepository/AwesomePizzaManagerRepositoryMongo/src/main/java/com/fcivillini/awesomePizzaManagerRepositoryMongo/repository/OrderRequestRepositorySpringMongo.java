package com.fcivillini.awesomePizzaManagerRepositoryMongo.repository;

import com.fcivillini.awesomePizzaManagerRepositoryMongo.entity.OrderRequestMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface OrderRequestRepositorySpringMongo extends MongoRepository<OrderRequestMongo, String> {


    @Query("{ 'reservationTime': { $gt: ?0, $lt: ?1 }, 'orderStatus': ?2 }")
    Collection<OrderRequestMongo> findByReservationTimeBetweenAndOrderStatusOrderByCreationDateAsc(final Date startTime, final Date endTime, final OrderStatusMongo orderStatus);

}
