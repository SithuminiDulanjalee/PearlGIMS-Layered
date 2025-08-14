package lk.ijse.pearlgims.dao.custom;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.dao.CrudDAO;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.entity.Production;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductionDAO extends CrudDAO<Production> {
    public boolean issue(String materialId, int quantity) throws SQLException, ClassNotFoundException ;

//    public String getNextId();
}
