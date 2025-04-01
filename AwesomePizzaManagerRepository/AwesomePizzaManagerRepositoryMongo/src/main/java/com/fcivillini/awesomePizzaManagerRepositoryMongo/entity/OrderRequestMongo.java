package com.fcivillini.awesomePizzaManagerRepositoryMongo.entity;

import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.OrderStatusMongo;
import com.fcivillini.awesomePizzaManagerRepositoryMongo.model.PizzaRequestMongo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@Document(collection = "pizzaOrder")
public class OrderRequestMongo {

    @Id
    protected String id;

    @Field
    protected String userName;

    @Field
    protected String phoneNumber;

    @Field
    protected OrderStatusMongo orderStatus;

    @Field
    protected List<PizzaRequestMongo> pizzaList;

    @Field(name = "creationDate")
    @CreatedDate
    protected Date creationDate;

    @Field(name = "updateDate")
    @LastModifiedDate
    protected Date updateDate;
}