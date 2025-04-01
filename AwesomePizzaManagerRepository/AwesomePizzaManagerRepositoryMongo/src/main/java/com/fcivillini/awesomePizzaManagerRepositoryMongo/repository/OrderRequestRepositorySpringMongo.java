package com.fcivillini.awesomePizzaManagerRepositoryMongo.repository;

import com.fcivillini.awesomePizzaManagerRepositoryMongo.entity.OrderRequestMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRequestRepositorySpringMongo extends MongoRepository<OrderRequestMongo, String> {

}
