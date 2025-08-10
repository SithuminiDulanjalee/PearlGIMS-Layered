package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;
}
