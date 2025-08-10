package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.SupplierBO;
import lk.ijse.pearlgims.dao.custom.SupplierDAO;
import lk.ijse.pearlgims.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pearlgims.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDAOImpl();
    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(supplierDTO);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplierDTO);
    }

    @Override
    public boolean deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierID);
    }

    @Override
    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextId();
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier(String search) throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll(search);
    }
}
