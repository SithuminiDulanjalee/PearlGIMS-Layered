package lk.ijse.pearlgims.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.QueryDAO;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.entity.Production;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<Production> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT production.*,raw_material.material_name from production JOIN raw_material ON production.material_id = raw_material.material_id");
        ArrayList<Production> productions = new ArrayList<>();

        while (resultSet.next()){
            Production entity = new Production(
                    resultSet.getString("production_id"),
                    resultSet.getString("material_id"),
                    resultSet.getString("material_name"),
                    resultSet.getInt("qty")
            );
            productions.add(entity);
        }

        return productions;
    }


}
