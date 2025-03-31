package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerInterface.dto.PizzaRequestDto;
import dto.PizzaRequestDao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = OrderStatusMapper.class)
public interface PizzaRequestMapper {

    public PizzaRequestMapper INSTANCE = Mappers.getMapper(PizzaRequestMapper.class);

    PizzaRequestDto toDto(PizzaRequest request);
    PizzaRequest fromDto(PizzaRequestDto request);


    PizzaRequestDao toDao(PizzaRequest request);
    PizzaRequest fromDao(PizzaRequestDao request);
}
