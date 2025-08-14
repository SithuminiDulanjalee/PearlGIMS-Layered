package lk.ijse.pearlgims.bo.custom;

import javafx.scene.control.Alert;
import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductionBO extends SuperBO {
    public boolean issue(String materialId, int quantity) throws SQLException, ClassNotFoundException;

    public String getNextProductionId();

    public ArrayList<ProductionDTO> getAllProduction() throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDetail> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<RawMaterialDTO> loadRawMaterial() throws SQLException, ClassNotFoundException ;
}
