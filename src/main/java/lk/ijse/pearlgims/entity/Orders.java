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

    public Orders(String orderId, String customerId, long time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = new Date(time);
    }
}
