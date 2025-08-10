package lk.ijse.pearlgims.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Orders {
    private String orderId;
    private String customerId;
    private Date orderDate;
    private ArrayList<OrderItem> orderItems;

}
