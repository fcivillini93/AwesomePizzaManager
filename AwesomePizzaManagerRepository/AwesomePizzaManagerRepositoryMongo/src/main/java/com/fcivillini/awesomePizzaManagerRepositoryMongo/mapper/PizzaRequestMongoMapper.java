package com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.PizzaRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.PizzaRequestMongo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = OrderStatusMongoMapper.class)
public interface PizzaRequestMongoMapper {

    public PizzaRequestMongoMapper INSTANCE = Mappers.getMapper(PizzaRequestMongoMapper.class);

    PizzaRequestDao toDao(PizzaRequestMongo request);

    PizzaRequestMongo fromDao(PizzaRequestDao request);
}
