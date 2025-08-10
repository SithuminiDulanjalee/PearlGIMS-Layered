package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dto.OrderItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemDAO {
    public boolean saveDetailsList(ArrayList<OrderItemDTO> orderItemList) throws SQLException, ClassNotFoundException;

    public boolean saveDetail(OrderItemDTO orderItemDTO) throws SQLException, ClassNotFoundException ;
}
