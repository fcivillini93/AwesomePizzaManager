package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerInterface.dto.EvolveOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = OrderStatusMapper.class)
public interface EvolveOrderMapper {

    public EvolveOrderMapper INSTANCE = Mappers.getMapper(EvolveOrderMapper.class);

    EvolveOrderDto toDto(EvolveOrder request);

    EvolveOrder fromDto(EvolveOrderDto request);

}
