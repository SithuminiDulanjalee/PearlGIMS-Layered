package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.SupplierDAO;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public ArrayList<Supplier> getAll(String search) throws SQLException, ClassNotFoundException {
        String searchQuery = "%" + search + "%";
        ResultSet result = SQLUtil.executeQuery("SELECT * FROM supplier Where name like ?", searchQuery);
        ArrayList<Supplier> supplierList = new ArrayList<>();
        while (result.next()) {
            Supplier entity = new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
            supplierList.add(entity);
        }
        return supplierList;
    }

    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        //String sql = "INSERT INTO supplier VALUES (?,?,?,?,?)";
        String sql = "INSERT INTO supplier (supplier_id, name, contact, email, address) VALUES (?, ?, ?, ?, ?)";

        return SQLUtil.executeUpdate(sql, entity.getSupplierID(), entity.getName(), entity.getContact(), entity.getEmail(), entity.getAddress());
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET name = ?, contact = ?, email = ?, address = ? WHERE supplier_id = ?";
        return SQLUtil.executeUpdate(sql, entity.getName(), entity.getContact(), entity.getEmail(), entity.getAddress(), entity.getSupplierID());
    }

    public boolean delete(String supplierID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        return SQLUtil.executeUpdate(sql, supplierID);
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select supplier_id from supplier order by supplier_id desc limit 1");

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber+1;

            String nextIdString = String.format("S%03d",nextIdNumber);
            return nextIdString;
        }
        return "S001";
    }

    public ArrayList<SupplierDTO> load() throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT supplier_id,name FROM supplier");
        ArrayList<SupplierDTO> supplierList = new ArrayList<>();
        while (result.next()) {
            SupplierDTO supplier = new SupplierDTO(
                    result.getString(1),
                    result.getString(2)
            );
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT COUNT(*) FROM supplier");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
