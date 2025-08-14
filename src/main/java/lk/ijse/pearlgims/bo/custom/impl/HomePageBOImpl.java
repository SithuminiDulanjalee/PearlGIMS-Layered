package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.HomePageBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.CustomerDAO;
import lk.ijse.pearlgims.dao.custom.OrderDAO;
import lk.ijse.pearlgims.dao.custom.SupplierDAO;

import java.sql.SQLException;
import java.util.Map;

public class HomePageBOImpl implements HomePageBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCount();
    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        return supplierDAO.getCount();
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getCount();
    }

    @Override
    public Map<String, Integer> getOrderCountByDate() throws SQLException, ClassNotFoundException {
        return orderDAO.getCountByDate();
    }
}
