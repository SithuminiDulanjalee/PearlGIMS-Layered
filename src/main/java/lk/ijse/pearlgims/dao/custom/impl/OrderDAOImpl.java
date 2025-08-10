package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.OrderDAO;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.OrdersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select order_id from orders order by order_id desc limit 1");
        char tableCharacter = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharacter + "001";
    }

    public boolean changeStatus(String orderId, String status) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update orders set status=? where order_id=?", status, orderId);
    }

    public OrdersDTO getById(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from orders where order_id=?", orderId);
        ResultSet orderItem = SQLUtil.executeQuery("select * from order_item where order_id=?", orderId);
        OrdersDTO order = new OrdersDTO();



        if (resultSet.next()) {
            ArrayList<OrderItemDTO> orderItems = new ArrayList<>();
            while (orderItem.next()) {
                OrderItemDTO orderItemDTO = new OrderItemDTO(
                        orderItem.getString("orderId"),
                        orderItem.getString("productId"),
                        orderItem.getInt("qty"),
                        orderItem.getDouble("price")
                );
                orderItems.add(orderItemDTO);
            }

            order = new OrdersDTO(
                    resultSet.getString("order_id"),
                    resultSet.getString("customer_id"),
                    new Date(resultSet.getDate("order_date").getTime()),
                    orderItems
            );
        }
        return order;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select count(*) from orders");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    public Map<String, Integer> getCountByDate() throws SQLException, ClassNotFoundException {
        Map<String, Integer> orderCountMap = new HashMap<>();
        ResultSet rst = SQLUtil.executeQuery("select date(order_date) as order_date, count(*) as count from orders group by date(order_date)");
        while (rst.next()) {
            orderCountMap.put(rst.getString("order_date"), rst.getInt("count"));
        }
        return orderCountMap;
    }

    @Override
    public boolean save(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("insert into orders values (?,?,?)",
                ordersDTO.getOrderId(),
                ordersDTO.getCustomerId(),
                ordersDTO.getOrderDate()
        );
    }

}
