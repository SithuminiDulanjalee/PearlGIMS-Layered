package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.OrderDetailDAO;
import lk.ijse.pearlgims.dto.OrderDetailDTO;
import lk.ijse.pearlgims.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT orders.order_id,orders.order_date,customer.name AS customer_name,COUNT(order_item.order_id) AS item_count,SUM(order_item.qty) AS total_item_quantity,SUM(order_item.price) AS total_price FROM orders JOIN customer ON orders.customer_id = customer.customer_id JOIN order_item ON orders.order_id = order_item.order_id GROUP BY orders.order_id");

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        while (resultSet.next()){
            OrderDetail entity = new OrderDetail(resultSet.getString(1),resultSet.getDate(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5));
            orderDetails.add(entity);
        }

        return orderDetails;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
