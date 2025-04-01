package com.fcivillini.awesomePizzaManagerRepositoryMongo.mapper;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.PizzaRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.configuration.AwesomePizzaManagerMongoMapperConfiguration;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.entity.OrderRequestMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.PizzaRequestMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = AwesomePizzaManagerMongoMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderRequestMongoMapperTest {
    @Autowired
    private OrderRequestMongoMapper mapper;

    @Test
    void test_toDAO() {
        LocalDateTime creationDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime updateDate = LocalDateTime.of(2020, 1, 1, 1, 0);
        OrderRequestMongo orderRequest = new OrderRequestMongo()
                .setId("id")
                .setUserName("user")
                .setPhoneNumber("phoneNumber")
                .setOrderStatus(OrderStatusMongo.PENDING)
                .setPizzaList(asList(
                        new PizzaRequestMongo().setName("Margherita").setOrderStatus(OrderStatusMongo.PENDING)
                ))
                .setCreationDate(Date.from(creationDate.atZone(ZoneId.systemDefault()).toInstant()))
                .setUpdateDate(Date.from(updateDate.atZone(ZoneId.systemDefault()).toInstant()));

        assertEquals(new OrderRequestDao()
                        .setId("id")
                        .setUserName("user")
                        .setPhoneNumber("phoneNumber")
                        .setOrderStatus(OrderStatusDao.PENDING)
                        .setPizzaList(asList(
                                new PizzaRequestDao().setName("Margherita").setOrderStatus(OrderStatusDao.PENDING)
                        ))
                        .setCreationDate(creationDate)
                        .setUpdateDate(updateDate),
                mapper.toDao(orderRequest));
    }

    @Test
    void test_fromDao() {

        LocalDateTime createDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime updateDate = LocalDateTime.of(2020, 1, 1, 1, 0);
        OrderRequestDao orderRequest = new OrderRequestDao()
                .setId("id")
                .setUserName("user")
                .setPhoneNumber("phoneNumber")
                .setOrderStatus(OrderStatusDao.PENDING)
                .setPizzaList(asList(
                        new PizzaRequestDao().setName("Margherita").setOrderStatus(OrderStatusDao.PENDING)
                ))
                .setCreationDate(createDate)
                .setUpdateDate(updateDate);

        assertEquals(new OrderRequestMongo()
                        .setId("id")
                        .setUserName("user")
                        .setPhoneNumber("phoneNumber")
                        .setOrderStatus(OrderStatusMongo.PENDING)
                        .setPizzaList(asList(
                                new PizzaRequestMongo().setName("Margherita").setOrderStatus(OrderStatusMongo.PENDING)
                        ))
                        .setCreationDate(Date.from(createDate.atZone(ZoneId.systemDefault()).toInstant()))
                        .setUpdateDate(Date.from(updateDate.atZone(ZoneId.systemDefault()).toInstant())),
                mapper.fromDao(orderRequest));
    }
}