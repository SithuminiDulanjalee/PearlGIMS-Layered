package lk.ijse.pearlgims.dao.custom;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.dao.SuperDAO;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.entity.Production;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<Production> getAll() throws SQLException, ClassNotFoundException;

}
