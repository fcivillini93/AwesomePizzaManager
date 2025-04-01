package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OrderStatusMapper.class, PizzaRequestMapper.class})
public interface OrderRequestMapper {

    OrderRequestMapper INSTANCE = Mappers.getMapper(OrderRequestMapper.class);

    OrderRequestDto toDto(OrderRequest request);
    OrderRequest fromDto(OrderRequestDto request);

    OrderRequestDao toDao(OrderRequest request);
    OrderRequest fromDao(OrderRequestDao request);
}
