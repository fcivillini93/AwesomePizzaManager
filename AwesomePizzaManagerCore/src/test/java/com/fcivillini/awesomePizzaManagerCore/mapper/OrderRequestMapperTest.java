package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.configuration.AwesomePizzaManagerMapperConfiguration;
import com.fcivillini.awesomePizzaManagerCore.model.OrderRequest;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderRequestDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.PizzaRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AwesomePizzaManagerMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderRequestMapperTest {
    @Autowired
    private OrderRequestMapper mapper;

    @Test
    void test_toDTO() {
        OrderRequest orderRequest = new OrderRequest()
                .setId("id")
                .setUserName("user")
                .setPhoneNumber("phoneNumber")
                .setOrderStatus(OrderStatus.PENDING)
                .setPizzaList(asList(
                        new PizzaRequest().setName("Margherita").setOrderStatus(OrderStatus.PENDING)
                ))
                .setCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdateDate(LocalDateTime.of(2020, 1, 1, 1, 0));

        assertEquals(new OrderRequestDto()
                .setId("id")
                .setUserName("user")
                .setPhoneNumber("phoneNumber")
                .setOrderStatus(OrderStatusDto.PENDING)
                .setPizzaList(asList(
                        new PizzaRequestDto().setName("Margherita").setOrderStatus(OrderStatusDto.PENDING)
                ))
                .setCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdateDate(LocalDateTime.of(2020, 1, 1, 1, 0)),
                mapper.toDto(orderRequest));
    }

    @Test
    void test_fromDto() {

        OrderRequestDto orderRequest = new OrderRequestDto()
                .setId("id")
                .setUserName("user")
                .setPhoneNumber("phoneNumber")
                .setOrderStatus(OrderStatusDto.PENDING)
                .setPizzaList(asList(
                        new PizzaRequestDto().setName("Margherita").setOrderStatus(OrderStatusDto.PENDING)
                ))
                .setCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdateDate(LocalDateTime.of(2020, 1, 1, 1, 0));

        assertEquals(new OrderRequest()
                        .setId("id")
                        .setUserName("user")
                        .setPhoneNumber("phoneNumber")
                        .setOrderStatus(OrderStatus.PENDING)
                        .setPizzaList(asList(
                                new PizzaRequest().setName("Margherita").setOrderStatus(OrderStatus.PENDING)
                        ))
                        .setCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdateDate(LocalDateTime.of(2020, 1, 1, 1, 0)),
                mapper.fromDto(orderRequest));
    }
}