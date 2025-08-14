package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.InventoryDetailDAO;
import lk.ijse.pearlgims.dto.tm.InventoryDetailsTm;
import lk.ijse.pearlgims.entity.InventoryDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDetailDAOImpl implements InventoryDetailDAO {
    public ArrayList<InventoryDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDetails> entity = new ArrayList<>();
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM inventory_detail");

        while (rs.next()) {
            entity.add(new InventoryDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        return entity;
    }

    @Override
    public boolean update(InventoryDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(InventoryDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO inventory_detail VALUES (?,?,?,?)",
                entity.getInventoryId(),
                entity.getInventoryId(),
                entity.getMaterialId(),
                entity.getQty()
        );
    }

    public String getNextId(){
        try {
            ResultSet resultSet = SQLUtil.executeQuery("select MAX(inventory_detail_id) from inventory_detail order by inventory_detail_id desc limit 1");
            String tableCharacter = "ID";
            if (resultSet.next()) {
                String lastId = resultSet.getString(1);
                String lastIdNumberString = lastId.substring(2);
                int lastIdNumber = Integer.parseInt(lastIdNumberString);
                int nextIdNumber = lastIdNumber + 1;
                String nextIdString = String.format(tableCharacter + "%04d", nextIdNumber);
                return nextIdString;
            }
            return tableCharacter + "0001";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getRawMaterialCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT SUM(qty) FROM inventory_detail");
        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;
    }

    public double getFinishedGoodsCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT SUM(qty) FROM finished_goods");
        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;
    }
}
