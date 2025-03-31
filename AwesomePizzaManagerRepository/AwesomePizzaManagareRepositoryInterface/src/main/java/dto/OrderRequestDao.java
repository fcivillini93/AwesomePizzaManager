package dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Accessors(chain = true)
public class OrderRequestDao {

    private String id;

    private String userName;

    private String phoneNumber;

    private OrderStatusDao orderStatus;

    private Collection<PizzaRequestDao> pizzaList;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}