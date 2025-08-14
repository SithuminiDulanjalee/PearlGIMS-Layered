package lk.ijse.pearlgims.dao.custom;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.InventoryDTO;
import lk.ijse.pearlgims.dto.tm.InventoryTM;
import lk.ijse.pearlgims.entity.Inventory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDAO extends CrudDAO<Inventory> {
//    public ArrayList<InventoryTM> getAll() throws SQLException, ClassNotFoundException;
//
//    public boolean save(InventoryTM inventoryTM) throws SQLException, ClassNotFoundException;
//
//    public String getNextId();

    public ArrayList<InventoryTM> load() throws SQLException, ClassNotFoundException;
}
