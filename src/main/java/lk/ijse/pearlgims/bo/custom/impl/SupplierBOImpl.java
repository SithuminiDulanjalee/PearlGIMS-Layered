package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.SupplierBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.SupplierDAO;
import lk.ijse.pearlgims.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierID(), supplierDTO.getName(), supplierDTO.getContact(), supplierDTO.getEmail(), supplierDTO.getAddress()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierID(), supplierDTO.getName(), supplierDTO.getContact(), supplierDTO.getEmail(), supplierDTO.getAddress()));
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
        ArrayList<Supplier> entity = supplierDAO.getAll(search);
        ArrayList<SupplierDTO> supplierDTO = new ArrayList<>();
        for (Supplier supplier : entity) {
            supplierDTO.add(new SupplierDTO(supplier.getSupplierID(), supplier.getName(),supplier.getContact(), supplier.getEmail(), supplier.getAddress()));
        }
        return supplierDTO;
    }
}