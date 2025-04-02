package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.configuration.AwesomePizzaManagerMapperConfiguration;
import com.fcivillini.awesomePizzaManagerCore.model.EvolveOrder;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerInterface.dto.EvolveOrderDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = AwesomePizzaManagerMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class EvolveOrderMapperTest {

    @Autowired
    private EvolveOrderMapper mapper;

    @Test
    void test_toDTO() {
        EvolveOrder request = new EvolveOrder()
                .setOrderId("orderId")
                .setPizzaOrderId("pizzaOrderId")
                .setOrderStatus(OrderStatus.PENDING);

        assertEquals(new EvolveOrderDto()
                .setOrderId("orderId")
                .setPizzaOrderId("pizzaOrderId")
                .setOrderStatus(OrderStatusDto.PENDING), mapper.toDto(request));
    }

    @Test
    void test_fromDto() {
        EvolveOrderDto request = new EvolveOrderDto()
                .setOrderId("orderId")
                .setPizzaOrderId("pizzaOrderId")
                .setOrderStatus(OrderStatusDto.PENDING);

        assertEquals(new EvolveOrder()
                .setOrderId("orderId")
                .setPizzaOrderId("pizzaOrderId")
                .setOrderStatus(OrderStatus.PENDING), mapper.fromDto(request));
    }

}