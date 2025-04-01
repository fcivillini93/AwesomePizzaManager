package com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatusMongoMapperTest {

    private OrderStatusMongoMapper mapper = OrderStatusMongoMapper.INSTANCE;

    @Test
    void test_toDao() {
        assertEquals(OrderStatusDao.PENDING, mapper.toDao(OrderStatusMongo.PENDING));
        assertEquals(OrderStatusDao.IN_PROGRESS, mapper.toDao(OrderStatusMongo.IN_PROGRESS));
        assertEquals(OrderStatusDao.READY, mapper.toDao(OrderStatusMongo.READY));
        assertEquals(OrderStatusDao.TO_PAY, mapper.toDao(OrderStatusMongo.TO_PAY));
        assertEquals(OrderStatusDao.FINISHED, mapper.toDao(OrderStatusMongo.FINISHED));
        assertEquals(OrderStatusDao.CANCELED, mapper.toDao(OrderStatusMongo.CANCELED));
    }

    @Test
    void test_fromDao() {
        assertEquals(OrderStatusMongo.PENDING, mapper.fromDao(OrderStatusDao.PENDING));
        assertEquals(OrderStatusMongo.IN_PROGRESS, mapper.fromDao(OrderStatusDao.IN_PROGRESS));
        assertEquals(OrderStatusMongo.READY, mapper.fromDao(OrderStatusDao.READY));
        assertEquals(OrderStatusMongo.TO_PAY, mapper.fromDao(OrderStatusDao.TO_PAY));
        assertEquals(OrderStatusMongo.FINISHED, mapper.fromDao(OrderStatusDao.FINISHED));
        assertEquals(OrderStatusMongo.CANCELED, mapper.fromDao(OrderStatusDao.CANCELED));
    }
}