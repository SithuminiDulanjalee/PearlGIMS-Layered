package lk.ijse.pearlgims.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RawMaterialDTO {
    private String materialId;
    private String materialName;
    private double price;
    private int qty;

    public RawMaterialDTO(String materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }
}
