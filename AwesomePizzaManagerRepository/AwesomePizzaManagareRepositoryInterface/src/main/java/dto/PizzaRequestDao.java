package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PizzaRequestDao {

    private String name;

    private OrderStatusDao orderStatus;
}