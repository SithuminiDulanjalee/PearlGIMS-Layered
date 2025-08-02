package lk.ijse.pearlgims.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDetailsTm {
    private String inventoryDetailId;
    private String inventoryId;
    private String materialId;
    private int qty;
}