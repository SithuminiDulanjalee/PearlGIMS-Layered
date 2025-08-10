package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dto.InventoryDetailDTO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RawMaterialDAO {
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException ;


    public RawMaterialDTO findById(String selectedRawMaterialId) throws SQLException, ClassNotFoundException ;

    public boolean reduceQty(InventoryDetailDTO inventoryDetailDTO) throws SQLException, ClassNotFoundException ;


    public String getNextId() throws SQLException, ClassNotFoundException ;

    public boolean save(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<RawMaterialDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean update(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException ;

    public boolean delete(String rawMaterialId) throws SQLException, ClassNotFoundException ;

    public ArrayList<RawMaterialDTO> load() throws SQLException, ClassNotFoundException ;
}
