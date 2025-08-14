package lk.ijse.pearlgims.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.InventoryDAO;
import lk.ijse.pearlgims.dto.tm.InventoryTM;
import lk.ijse.pearlgims.entity.Inventory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {
    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> entity = new ArrayList<>();
        ResultSet rs = SQLUtil.executeQuery("select * from inventory");

        while (rs.next()) {
            entity.add(
                    new Inventory(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            new Date(rs.getDate(4).getTime())
                    ));
        }
        return entity;
    }

    @Override
    public boolean update(Inventory entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(Inventory entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("insert into inventory values (?,?,?,?)",
                entity.getInventoryId(),
                entity.getName(),
                entity.getSupplierId(),
                entity.getDate()
        );
    }

    public String getNextId(){
        try {
            ResultSet resultSet = SQLUtil.executeQuery("select MAX(inventory_id) from inventory order by inventory_id desc limit 1");
            char tableCharacter = 'I';
            if (resultSet.next()) {
                String lastId = resultSet.getString(1);
                String lastIdNumberString = lastId.substring(1);
                int lastIdNumber = Integer.parseInt(lastIdNumberString);
                int nextIdNumber = lastIdNumber + 1;
                String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);
                return nextIdString;
            }
            return tableCharacter + "001";
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<InventoryTM> load() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryTM> inventoryTMArrayList = new ArrayList<>();
        ResultSet rs = SQLUtil.executeQuery("select inventory_id,name from inventory");

        while (rs.next()) {
            inventoryTMArrayList.add(new InventoryTM(rs.getString(1),rs.getString(2)));
        }
        return inventoryTMArrayList;


    }
}
