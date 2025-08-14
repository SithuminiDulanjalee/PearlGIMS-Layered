package lk.ijse.pearlgims.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.bo.custom.InventoryBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.InventoryDAO;
import lk.ijse.pearlgims.dao.custom.InventoryDetailDAO;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dao.custom.SupplierDAO;
import lk.ijse.pearlgims.dao.custom.impl.InventoryDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.InventoryDetailDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pearlgims.dto.CustomerDTO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.dto.tm.InventoryDetailsTm;
import lk.ijse.pearlgims.dto.tm.InventoryTM;
import lk.ijse.pearlgims.entity.Customer;
import lk.ijse.pearlgims.entity.Inventory;
import lk.ijse.pearlgims.entity.InventoryDetails;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVENTORY);
    InventoryDetailDAO inventoryDetailDAO = (InventoryDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVENTORY_DETAIL);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    RawMaterialDAO rawMaterialDAO = (RawMaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RAW_MATERIAL);
    public ArrayList<InventoryTM> getAllInventory() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> entity =inventoryDAO.getAll();
        ArrayList<InventoryTM> inventoryTMS=new ArrayList<>();
        for (Inventory i:entity){
            inventoryTMS.add(new InventoryTM(i.getInventoryId(),i.getName(),i.getSupplierId(),i.getDate()));
        }
        return inventoryTMS;
    }


    public boolean saveInventory(InventoryTM inventoryTM) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(inventoryTM.getInventoryId(),inventoryTM.getName(),inventoryTM.getSupplierId(),new Date(inventoryTM.getDate().getTime())));
    }

    public String getNextInventoryId(){
        try {
            return inventoryDAO.getNextId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<InventoryTM> loadInventory() throws SQLException, ClassNotFoundException {
        return inventoryDAO.load();


    }




    public ArrayList<InventoryDetailsTm> getAllInventoryDetaills() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDetails> entity =inventoryDetailDAO.getAll();
        ArrayList<InventoryDetailsTm> inventoryDetailsTms=new ArrayList<>();
        for (InventoryDetails i:entity){
            inventoryDetailsTms.add(new InventoryDetailsTm(i.getInventoryDetailId(),i.getInventoryId(),i.getMaterialId(),i.getQty()));
        }
        return inventoryDetailsTms;
    }

    public boolean saveInventoryDetails(InventoryDetailsTm inventoryDetailsTm) throws SQLException, ClassNotFoundException {
        return inventoryDetailDAO.save(new InventoryDetails(inventoryDetailsTm.getInventoryDetailId(),inventoryDetailsTm.getInventoryId(),inventoryDetailsTm.getMaterialId(),inventoryDetailsTm.getQty()));
    }

    public String getNextInventoryDetailsId(){
        try {
            return inventoryDetailDAO.getNextId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getRawMaterialCount() throws SQLException, ClassNotFoundException {
        return inventoryDetailDAO.getRawMaterialCount();
    }

    public double getFinishedGoodsCount() throws SQLException, ClassNotFoundException {
        return inventoryDetailDAO.getFinishedGoodsCount();
    }

    @Override
    public ArrayList<SupplierDTO> loadSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.load();
    }

    @Override
    public ArrayList<RawMaterialDTO> loadRawMaterial() throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.load();
    }

}
