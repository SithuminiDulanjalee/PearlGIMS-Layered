package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.OrderBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.CustomerDAO;
import lk.ijse.pearlgims.dao.custom.OrderDAO;
import lk.ijse.pearlgims.dao.custom.OrderItemDAO;
import lk.ijse.pearlgims.dao.custom.ProductDAO;
import lk.ijse.pearlgims.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.OrderItemDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.ProductDAOImpl;
import lk.ijse.pearlgims.db.DBConnection;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.OrdersDTO;
import lk.ijse.pearlgims.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_ITEM);

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllIds();
    }

    @Override
    public String findNameByCustomerId(String selectedCustomerId) throws SQLException, ClassNotFoundException {
        return customerDAO.findNameById(selectedCustomerId);
    }

    @Override
    public ArrayList<String> getAllProductIds() throws SQLException, ClassNotFoundException {
        return productDAO.getAllIds();
    }

    @Override
    public ArrayList<String> getAllProductSizes() throws SQLException, ClassNotFoundException {
        return productDAO.getAllSizes();
    }

    @Override
    public ProductDTO findByProductId(String selectedProductId) throws SQLException, ClassNotFoundException {
        return productDAO.findById(selectedProductId);
    }

    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();
    }

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isOrderSave = orderDAO.save(ordersDTO);
            System.out.println("isOrderSave = " + isOrderSave);

            if (isOrderSave) {
                boolean isOrderDetailsSaved = orderItemDAO.saveDetailsList(ordersDTO.getOrderItems());
                System.out.println("isOrderDetailsSaved = " + isOrderDetailsSaved);
                System.out.println("isOrderSave = " + isOrderSave);
                if (isOrderDetailsSaved) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            }
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

}
