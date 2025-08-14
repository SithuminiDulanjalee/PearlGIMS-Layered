package lk.ijse.pearlgims.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDetails {
    private String inventoryDetailId;
    private String inventoryId;
    private String materialId;
    private int qty;
}