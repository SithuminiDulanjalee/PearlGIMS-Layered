package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RawMaterialBO extends SuperBO {
    public String getNextRawMaterialId() throws SQLException, ClassNotFoundException ;

    public boolean saveRawMaterial(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<RawMaterialDTO> getAllRawMaterial() throws SQLException, ClassNotFoundException ;

    public boolean updateRawMaterial(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException ;

    public boolean deleteRawMaterial(String rawMaterialId) throws SQLException, ClassNotFoundException ;
}
