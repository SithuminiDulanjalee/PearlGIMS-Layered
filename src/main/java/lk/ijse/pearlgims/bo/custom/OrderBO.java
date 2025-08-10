package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.db.DBConnection;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.OrdersDTO;
import lk.ijse.pearlgims.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;

    public String findNameByCustomerId(String selectedCustomerId) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllProductIds() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllProductSizes() throws SQLException, ClassNotFoundException;

    public ProductDTO findByProductId(String selectedProductId) throws SQLException, ClassNotFoundException;

    public String getNextOrderId() throws SQLException, ClassNotFoundException;

    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;

}
