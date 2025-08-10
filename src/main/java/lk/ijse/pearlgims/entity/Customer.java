package lk.ijse.pearlgims.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String customerID;
    private String name;
    private String contact;
    private String email;
    private String address;
}
