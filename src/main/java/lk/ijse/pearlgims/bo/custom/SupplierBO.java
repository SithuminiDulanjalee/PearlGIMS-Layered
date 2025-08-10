package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException ;

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException ;

    public String getNextSupplierId() throws SQLException, ClassNotFoundException;

    public ArrayList<SupplierDTO> getAllSupplier(String search) throws SQLException, ClassNotFoundException;
}
