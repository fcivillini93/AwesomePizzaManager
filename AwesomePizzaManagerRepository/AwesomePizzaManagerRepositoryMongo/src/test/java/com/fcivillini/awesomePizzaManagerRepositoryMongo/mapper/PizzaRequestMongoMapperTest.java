package com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.PizzaRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.configuration.AwesomePizzaManagerMongoMapperConfiguration;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.PizzaRequestMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = AwesomePizzaManagerMongoMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class PizzaRequestMongoMapperTest {

    @Autowired
    private PizzaRequestMongoMapper mapper;

    @Test
    void test_toDao() {
        PizzaRequestMongo request = new PizzaRequestMongo()
                .setName("Margherita")
                .setOrderStatus(OrderStatusMongo.PENDING);

        assertEquals(new PizzaRequestDao().setName("Margherita").setOrderStatus(OrderStatusDao.PENDING), mapper.toDao(request));
    }

    @Test
    void test_fromDao() {
        PizzaRequestDao request = new PizzaRequestDao()
                .setName("Margherita")
                .setOrderStatus(OrderStatusDao.PENDING);

        assertEquals(new PizzaRequestMongo().setName("Margherita").setOrderStatus(OrderStatusMongo.PENDING), mapper.fromDao(request));
    }

}