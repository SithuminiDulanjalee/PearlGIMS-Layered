package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.custom.SupplierDAO;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public ArrayList<SupplierDTO> getAll(String search) throws SQLException, ClassNotFoundException {
        String searchQuery = "%" + search + "%";
        ResultSet result = CrudUtil.execute("SELECT * FROM supplier Where name like ?", searchQuery);
        ArrayList<SupplierDTO> supplierList = new ArrayList<>();
        while (result.next()) {
            SupplierDTO supplier = new SupplierDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, supplierDTO.getSupplierID(), supplierDTO.getName(), supplierDTO.getContact(), supplierDTO.getEmail(), supplierDTO.getAddress());
    }

    public boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET name = ?, contact = ?, email = ?, address = ? WHERE supplier_id = ?";
        return CrudUtil.execute(sql, supplierDTO.getName(), supplierDTO.getContact(), supplierDTO.getEmail(), supplierDTO.getAddress(), supplierDTO.getSupplierID());
    }

    public boolean delete(String supplierID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        return CrudUtil.execute(sql, supplierID);
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select supplier_id from supplier order by supplier_id desc limit 1");

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
        ResultSet result = CrudUtil.execute("SELECT supplier_id,name FROM supplier");
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
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(*) FROM supplier");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
