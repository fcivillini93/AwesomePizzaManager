package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.configuration.AwesomePizzaManagerMapperConfiguration;
import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerCore.model.PizzaRequest;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import com.fcivillini.awesomePizzaManagerInterface.dto.PizzaRequestDto;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.PizzaRequestDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = AwesomePizzaManagerMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class PizzaRequestMapperTest {

    @Autowired
    private PizzaRequestMapper mapper;

    @Test
    void test_toDTO() {
        PizzaRequest request = new PizzaRequest()
                .setName("Margherita")
                .setOrderStatus(OrderStatus.PENDING);

        assertEquals(new PizzaRequestDto().setName("Margherita").setOrderStatus(OrderStatusDto.PENDING), mapper.toDto(request));
    }

    @Test
    void test_fromDto() {
        PizzaRequestDto request = new PizzaRequestDto()
                .setName("Margherita")
                .setOrderStatus(OrderStatusDto.PENDING);

        assertEquals(new PizzaRequest().setName("Margherita").setOrderStatus(OrderStatus.PENDING), mapper.fromDto(request));
    }

    @Test
    void test_toDao() {
        PizzaRequest request = new PizzaRequest()
                .setName("Margherita")
                .setOrderStatus(OrderStatus.PENDING);

        assertEquals(new PizzaRequestDao().setName("Margherita").setOrderStatus(OrderStatusDao.PENDING), mapper.toDao(request));
    }

    @Test
    void test_fromDao() {
        PizzaRequestDao request = new PizzaRequestDao()
                .setName("Margherita")
                .setOrderStatus(OrderStatusDao.PENDING);

        assertEquals(new PizzaRequest().setName("Margherita").setOrderStatus(OrderStatus.PENDING), mapper.fromDao(request));
    }

}