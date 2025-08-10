package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.CustomerBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.CustomerDAO;
import lk.ijse.pearlgims.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pearlgims.dto.CustomerDTO;
import lk.ijse.pearlgims.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> entity =customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTO=new ArrayList<>();
        for (Customer c:entity){
            customerDTO.add(new CustomerDTO(c.getCustomerID(),c.getName(),c.getContact(),c.getEmail(),c.getAddress()));
        }
        return customerDTO;
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextId();
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(
                new Customer(
                        customerDTO.getCustomerID(),
                        customerDTO.getName(),
                        customerDTO.getContact(),
                        customerDTO.getEmail(),
                        customerDTO.getAddress()
                ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(
                new Customer(
                        customerDTO.getCustomerID(),
                        customerDTO.getName(),
                        customerDTO.getContact(),
                        customerDTO.getEmail(),
                        customerDTO.getAddress()
                ));
    }

    @Override
    public boolean deleteCustomer(String customerID) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerID);
    }

}
