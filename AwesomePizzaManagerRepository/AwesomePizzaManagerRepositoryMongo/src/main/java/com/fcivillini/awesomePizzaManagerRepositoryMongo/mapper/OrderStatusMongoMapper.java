package com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderStatusMongoMapper {

    public OrderStatusMongoMapper INSTANCE = Mappers.getMapper(OrderStatusMongoMapper.class);

    OrderStatusDao toDao(OrderStatusMongo orderStatus);

    OrderStatusMongo fromDao(OrderStatusDao orderStatus);
}
