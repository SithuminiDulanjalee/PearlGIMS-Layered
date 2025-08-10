package lk.ijse.pearlgims.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private String productId;
    private String name;
    private double price;
    private int qty;
    private String status;
    private String size;

}
