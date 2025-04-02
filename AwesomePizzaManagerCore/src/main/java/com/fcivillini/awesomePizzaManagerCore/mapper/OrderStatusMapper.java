package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    public OrderStatusMapper INSTANCE = Mappers.getMapper(OrderStatusMapper.class);

    OrderStatusDto toDto(OrderStatus orderStatus);

    OrderStatus fromDto(OrderStatusDto orderStatus);


    OrderStatusDao toDao(OrderStatus orderStatus);

    OrderStatus fromDao(OrderStatusDao orderStatus);
}
