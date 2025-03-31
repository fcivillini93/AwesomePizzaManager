package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    public OrderStatusMapper INSTANCE = Mappers.getMapper(OrderStatusMapper.class);

    OrderStatusDto toDto(OrderStatus orderStatus);
    OrderStatus fromDto(OrderStatusDto orderStatus);

}
