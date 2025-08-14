package lk.ijse.pearlgims.bo.custom;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.dto.tm.InventoryDetailsTm;
import lk.ijse.pearlgims.dto.tm.InventoryTM;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {
    public ArrayList<InventoryTM> getAllInventory() throws SQLException, ClassNotFoundException;

    public boolean saveInventory(InventoryTM inventoryTM) throws SQLException, ClassNotFoundException;

    public String getNextInventoryId();

    public ArrayList<InventoryTM> loadInventory() throws SQLException, ClassNotFoundException;



    public ArrayList<InventoryDetailsTm> getAllInventoryDetaills() throws SQLException, ClassNotFoundException;

    public boolean saveInventoryDetails(InventoryDetailsTm inventoryDetailsTm) throws SQLException, ClassNotFoundException;

    public String getNextInventoryDetailsId();

    public double getRawMaterialCount() throws SQLException, ClassNotFoundException ;

    public double getFinishedGoodsCount() throws SQLException, ClassNotFoundException;

    public ArrayList<SupplierDTO> loadSupplier() throws SQLException, ClassNotFoundException ;

    public ArrayList<RawMaterialDTO> loadRawMaterial() throws SQLException, ClassNotFoundException;
}
