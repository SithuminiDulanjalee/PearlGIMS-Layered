package lk.ijse.pearlgims.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {
    private String orderId;
    private String productId;
    private int qty;
    private double price;
}
