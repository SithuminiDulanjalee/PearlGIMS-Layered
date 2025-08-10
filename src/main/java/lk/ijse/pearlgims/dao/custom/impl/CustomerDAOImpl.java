package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.CustomerDAO;
import lk.ijse.pearlgims.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select customer_id from customer order by customer_id desc limit 1");

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber+1;

            String nextIdString = String.format("C%03d",nextIdNumber);
            return nextIdString;
        }
        return "C001";
    }

    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "insert into customer values (?,?,?,?,?)",
                customerDTO.getCustomerID(),
                customerDTO.getName(),
                customerDTO.getContact(),
                customerDTO.getEmail(),
                customerDTO.getAddress()
        );
    }

    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from customer");

        ArrayList<CustomerDTO> customerDTOArrayList = new ArrayList<>();
        while (resultSet.next()){
            CustomerDTO customerDTO = new CustomerDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            customerDTOArrayList.add(customerDTO);
        }

        return customerDTOArrayList;
    }

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update customer set name=?, contact=?, email=?, address=? where customer_id=?",
                customerDTO.getName(),
                customerDTO.getContact(),
                customerDTO.getEmail(),
                customerDTO.getAddress(),
                customerDTO.getCustomerID()
        );
    }

    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("delete from customer where customer_id=?",customerID);
    }

    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select customer_id from customer");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }

    public String findNameById(String selectedCustomerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery(
                "select name from customer where customer_id=?",
                selectedCustomerId
        );

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select count(*) from customer");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

}
