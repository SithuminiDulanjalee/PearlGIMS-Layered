package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {
    public ArrayList<Supplier> getAll(String search) throws SQLException, ClassNotFoundException;

//    public boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException ;
//
//    public boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
//
//    public boolean delete(String supplierID) throws SQLException, ClassNotFoundException ;
//
//    public String getNextId() throws SQLException, ClassNotFoundException;

    public ArrayList<SupplierDTO> load() throws SQLException, ClassNotFoundException;

    public int getCount() throws SQLException, ClassNotFoundException ;
}
