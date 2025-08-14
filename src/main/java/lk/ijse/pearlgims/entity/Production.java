package lk.ijse.pearlgims.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Production {
    private String productionId;
    private String materialId;
    private String materialName;
    private int qty;
}
