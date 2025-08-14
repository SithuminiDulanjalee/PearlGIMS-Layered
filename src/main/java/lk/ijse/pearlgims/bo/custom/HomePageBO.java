package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface HomePageBO extends SuperBO {
    public int getCustomerCount() throws SQLException, ClassNotFoundException ;

    public int getSupplierCount() throws SQLException, ClassNotFoundException;

    public int getOrderCount() throws SQLException, ClassNotFoundException;

    public Map<String, Integer> getOrderCountByDate() throws SQLException, ClassNotFoundException ;
}
