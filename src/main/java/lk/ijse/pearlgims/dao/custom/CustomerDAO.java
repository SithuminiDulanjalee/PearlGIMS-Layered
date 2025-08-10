package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {
//    public String getNextId() throws SQLException, ClassNotFoundException ;
//
//    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
//
//    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
//
//    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;
//
//    public boolean delete(String customerID) throws SQLException, ClassNotFoundException ;

    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException;

    public String findNameById(String selectedCustomerId) throws SQLException, ClassNotFoundException;

    public int getCount() throws SQLException, ClassNotFoundException;

}
