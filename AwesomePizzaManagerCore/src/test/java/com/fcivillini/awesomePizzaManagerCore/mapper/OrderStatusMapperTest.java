package com.fcivillini.awesomePizzaManagerCore.mapper;

import com.fcivillini.awesomePizzaManagerCore.model.OrderStatus;
import com.fcivillini.awesomePizzaManagerInterface.dto.OrderStatusDto;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatusMapperTest {

    private OrderStatusMapper mapper = OrderStatusMapper.INSTANCE;

    @Test
    void test_toDto() {
        assertEquals(OrderStatusDto.PENDING, mapper.toDto(OrderStatus.PENDING));
        assertEquals(OrderStatusDto.IN_PROGRESS, mapper.toDto(OrderStatus.IN_PROGRESS));
        assertEquals(OrderStatusDto.READY, mapper.toDto(OrderStatus.READY));
        assertEquals(OrderStatusDto.TO_PAY, mapper.toDto(OrderStatus.TO_PAY));
        assertEquals(OrderStatusDto.FINISHED, mapper.toDto(OrderStatus.FINISHED));
        assertEquals(OrderStatusDto.CANCELED, mapper.toDto(OrderStatus.CANCELED));
    }

    @Test
    void test_fromDto() {
        assertEquals(OrderStatus.PENDING, mapper.fromDto(OrderStatusDto.PENDING));
        assertEquals(OrderStatus.IN_PROGRESS, mapper.fromDto(OrderStatusDto.IN_PROGRESS));
        assertEquals(OrderStatus.READY, mapper.fromDto(OrderStatusDto.READY));
        assertEquals(OrderStatus.TO_PAY, mapper.fromDto(OrderStatusDto.TO_PAY));
        assertEquals(OrderStatus.FINISHED, mapper.fromDto(OrderStatusDto.FINISHED));
        assertEquals(OrderStatus.CANCELED, mapper.fromDto(OrderStatusDto.CANCELED));
    }


    @Test
    void test_toDao() {
        assertEquals(OrderStatusDao.PENDING, mapper.toDao(OrderStatus.PENDING));
        assertEquals(OrderStatusDao.IN_PROGRESS, mapper.toDao(OrderStatus.IN_PROGRESS));
        assertEquals(OrderStatusDao.READY, mapper.toDao(OrderStatus.READY));
        assertEquals(OrderStatusDao.TO_PAY, mapper.toDao(OrderStatus.TO_PAY));
        assertEquals(OrderStatusDao.FINISHED, mapper.toDao(OrderStatus.FINISHED));
        assertEquals(OrderStatusDao.CANCELED, mapper.toDao(OrderStatus.CANCELED));
    }

    @Test
    void test_fromDao() {
        assertEquals(OrderStatus.PENDING, mapper.fromDao(OrderStatusDao.PENDING));
        assertEquals(OrderStatus.IN_PROGRESS, mapper.fromDao(OrderStatusDao.IN_PROGRESS));
        assertEquals(OrderStatus.READY, mapper.fromDao(OrderStatusDao.READY));
        assertEquals(OrderStatus.TO_PAY, mapper.fromDao(OrderStatusDao.TO_PAY));
        assertEquals(OrderStatus.FINISHED, mapper.fromDao(OrderStatusDao.FINISHED));
        assertEquals(OrderStatus.CANCELED, mapper.fromDao(OrderStatusDao.CANCELED));
    }
}