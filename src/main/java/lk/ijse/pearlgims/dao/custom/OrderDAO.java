package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.OrdersDTO;
import lk.ijse.pearlgims.entity.Orders;

import java.sql.SQLException;
import java.util.Map;

public interface OrderDAO extends CrudDAO<Orders> {
//    public String getNextId() throws SQLException, ClassNotFoundException;

    public boolean changeStatus(String orderId, String status) throws SQLException, ClassNotFoundException ;

    public OrdersDTO getById(String orderId) throws SQLException, ClassNotFoundException ;

    public int getCount() throws SQLException, ClassNotFoundException;

    public Map<String, Integer> getCountByDate() throws SQLException, ClassNotFoundException ;

//    public boolean save(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException ;

}
