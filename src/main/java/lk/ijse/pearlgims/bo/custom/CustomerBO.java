package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public String getNextCustomerId() throws SQLException, ClassNotFoundException ;

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(String customerID) throws SQLException, ClassNotFoundException ;

}
