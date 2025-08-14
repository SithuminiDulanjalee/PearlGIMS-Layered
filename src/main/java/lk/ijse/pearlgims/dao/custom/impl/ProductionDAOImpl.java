package lk.ijse.pearlgims.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.ProductionDAO;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.entity.Production;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionDAOImpl implements ProductionDAO {
    public boolean issue(String materialId, int quantity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO production VALUES(?,?,?)",getNextId(),materialId,quantity);
    }

    public String getNextId(){
        try {
            ResultSet resultSet = SQLUtil.executeQuery("select MAX(production_id) from production order by production_id desc limit 1");
            String tableCharacter = "PR";
            if (resultSet.next()) {
                String lastId = resultSet.getString(1);
                String lastIdNumberString = lastId.substring(2);
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

    @Override
    public boolean save(Production entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Production> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Production entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
