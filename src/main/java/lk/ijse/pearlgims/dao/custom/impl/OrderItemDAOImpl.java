package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.OrderItemDAO;
import lk.ijse.pearlgims.dao.custom.ProductDAO;
import lk.ijse.pearlgims.dto.OrderItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDAOImpl implements OrderItemDAO {
    private ProductDAO productDAO = new ProductDAOImpl();
    public boolean saveDetailsList(ArrayList<OrderItemDTO> orderItemList) throws SQLException, ClassNotFoundException {
        for (OrderItemDTO orderItemDTO : orderItemList) {
            boolean isOrderDetailsSaved = save(orderItemDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = productDAO.reduceQty(orderItemDTO);
            if (!isItemUpdated) {
                return false;
            }


        }
        return true;
    }

    public boolean save(OrderItemDTO orderItemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "insert into order_item values (?,?,?,?)",
                orderItemDTO.getOrderId(),
                orderItemDTO.getProductId(),
                orderItemDTO.getQty(),
                orderItemDTO.getPrice()
        );
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<OrderItemDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderItemDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
