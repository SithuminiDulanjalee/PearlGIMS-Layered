package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.tm.InventoryDetailsTm;
import lk.ijse.pearlgims.entity.InventoryDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDetailDAO extends CrudDAO<InventoryDetails> {
//    public ArrayList<InventoryDetailsTm> getAll() throws SQLException, ClassNotFoundException;
//
//    public boolean save(InventoryDetailsTm inventoryDetailsTm) throws SQLException, ClassNotFoundException;
//
//    public String getNextId();

    public double getRawMaterialCount() throws SQLException, ClassNotFoundException;

    public double getFinishedGoodsCount() throws SQLException, ClassNotFoundException;
}
