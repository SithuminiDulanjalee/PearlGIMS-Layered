package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.OrderItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemDAO extends CrudDAO<OrderItemDTO> {
    public boolean saveDetailsList(ArrayList<OrderItemDTO> orderItemList) throws SQLException, ClassNotFoundException;

    public boolean reduceQty(OrderItemDTO orderItemDTO) throws SQLException, ClassNotFoundException ;
//    public boolean save(OrderItemDTO orderItemDTO) throws SQLException, ClassNotFoundException ;
}
