package com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.entity.OrderRequestMongo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Mapper(componentModel = "spring", uses = {OrderStatusMongoMapper.class, PizzaRequestMongoMapper.class})
public interface OrderRequestMongoMapper {

    OrderRequestMongoMapper INSTANCE = Mappers.getMapper(OrderRequestMongoMapper.class);

    @Mapping(target = "creationDate", source = "creationDate", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "updateDate", source = "updateDate", qualifiedByName = "toLocalDateTime")
    OrderRequestDao toDao(OrderRequestMongo request);

    @Mapping(target = "creationDate", source = "creationDate", qualifiedByName = "toDate")
    @Mapping(target = "updateDate", source = "updateDate", qualifiedByName = "toDate")
    OrderRequestMongo fromDao(OrderRequestDao request);

    @Named("toLocalDateTime")
    default LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Named("toDate")
    default Date toDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

}
