package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.db.DBConnection;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.OrdersDTO;
import lk.ijse.pearlgims.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface OrderDAO {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public boolean changeStatus(String orderId, String status) throws SQLException, ClassNotFoundException ;

    public OrdersDTO getById(String orderId) throws SQLException, ClassNotFoundException ;

    public int getCount() throws SQLException, ClassNotFoundException;

    public Map<String, Integer> getCountByDate() throws SQLException, ClassNotFoundException ;

    public boolean save(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException ;
}
