package lk.ijse.pearlgims.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private String supplierID;
    private String name;
    private String contact;
    private String email;
    private String address;
}
