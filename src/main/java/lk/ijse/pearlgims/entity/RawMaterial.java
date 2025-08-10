package lk.ijse.pearlgims.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RawMaterial {
    private String materialId;
    private String materialName;
    private double price;
    private int qty;

    public RawMaterial(String materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }
}
