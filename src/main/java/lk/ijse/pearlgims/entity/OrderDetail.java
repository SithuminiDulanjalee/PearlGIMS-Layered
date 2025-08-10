package lk.ijse.pearlgims.entity;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private String orderId;
    private Date orderDate;
    private String customerName;
    private int productCount;
    private int totalProductQuantity;
}
