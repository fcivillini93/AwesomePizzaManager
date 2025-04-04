package com.fcivillini.awesomePizzaManagerRepositoryMongo.repository;

import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.OrderStatusDao;
import com.fcivillini.awesomePizzaManagerRepositoryInterface.dao.PizzaRequestDao;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.entity.OrderRequestMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.util.AbstractMongoTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderRequestRepositoryMongoTest extends AbstractMongoTest {

    @Autowired
    protected OrderRequestRepositorySpringMongo springRepository;

    @Autowired
    protected OrderRequestRepositoryMongo repository;

    @BeforeEach
    void setUp() {
        springRepository.deleteAll();
        assertEquals(0, springRepository.count());
    }

    @AfterEach
    void cleanUp() {
        springRepository.deleteAll();
        assertEquals(0, springRepository.count());
    }

    @Test
    void test_save() {
        OrderRequestDao result = repository.save(new OrderRequestDao()
                .setUserName("user")
                .setPhoneNumber("333-3333333")
                .setOrderStatus(OrderStatusDao.PENDING)
                .setPizzaList(asList(
                        new PizzaRequestDao().setName("pizza-name").setOrderStatus(OrderStatusDao.PENDING)
                ))

        );
        List<OrderRequestMongo> all = springRepository.findAll();
        assertEquals(1, all.size());
        assertEquals(result.getId(), all.get(0).getId());
        assertEquals(result.getUserName(), all.get(0).getUserName());
        assertEquals(result.getPhoneNumber(), all.get(0).getPhoneNumber());
        assertEquals(result.getOrderStatus().toString(), all.get(0).getOrderStatus().toString());
        assertEquals(result.getPizzaList().get(0).getName(), all.get(0).getPizzaList().get(0).getName());
        assertEquals(result.getPizzaList().get(0).getOrderStatus().toString(), all.get(0).getPizzaList().get(0).getOrderStatus().toString());
        assertEquals(result.getCreationDate(), all.get(0).getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        assertEquals(result.getUpdateDate(), all.get(0).getUpdateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

    }

    @Test
    void test_findById() {
        springRepository.save(new OrderRequestMongo().setId("id-1"));
        assertEquals(Optional.empty(), repository.findById("fake-id"));

        Optional<OrderRequestDao> result = repository.findById("id-1");
        assertTrue(result.isPresent());
        assertEquals("id-1", result.get().getId());
    }

    @Test
    void test_findFirstByCreationDate() {
        springRepository.save(new OrderRequestMongo().setUserName("user-1").setOrderStatus(OrderStatusMongo.PENDING)
                .setReservationTime(Date.from(LocalDateTime.of(2020, 1, 1, 1, 0).atZone(ZoneId.systemDefault()).toInstant())));
        springRepository.save(new OrderRequestMongo().setUserName("user-2").setOrderStatus(OrderStatusMongo.PENDING)
                .setReservationTime(Date.from(LocalDateTime.of(2020, 1, 1, 1, 10).atZone(ZoneId.systemDefault()).toInstant())));
        springRepository.save(new OrderRequestMongo().setUserName("user-3").setOrderStatus(OrderStatusMongo.PENDING)
                .setReservationTime(Date.from(LocalDateTime.of(2020, 1, 1, 1, 20).atZone(ZoneId.systemDefault()).toInstant())));
        assertEquals(3, springRepository.count());

        Optional<OrderRequestDao> result = repository.findFirstByCreationDate(
                LocalDateTime.of(2020, 1, 1, 1, 5),
                LocalDateTime.of(2020, 1, 1, 1, 25),
                OrderStatusDao.PENDING);

        assertTrue(result.isPresent());
        assertEquals("user-2", result.get().getUserName());

    }
}