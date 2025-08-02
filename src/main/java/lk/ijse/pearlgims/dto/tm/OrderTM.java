package lk.ijse.pearlgims.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTM {
    private String productId;
    private String productName;
    private int orderQty;
    private String size;
    private double price;
    private double total;
    private Button btnRemove;

}
