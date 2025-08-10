package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dto.InventoryDetailDTO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RawMaterialDAOImpl implements RawMaterialDAO {
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select material_id from raw_material");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }


    public RawMaterialDTO findById(String selectedRawMaterialId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery(
                "select * from raw_material where material_id=?",
                selectedRawMaterialId
        );
        if (rst.next()) {
            return new RawMaterialDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    public boolean reduceQty(InventoryDetailDTO inventoryDetailDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "update raw_material set qty = qty - ? where material_id = ?",
                inventoryDetailDTO.getQty(),
                inventoryDetailDTO.getMaterialId()
        );
    }


    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select material_id from raw_material order by material_id desc limit 1");

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber+1;

            String nextIdString = String.format("R%03d",nextIdNumber);
            return nextIdString;
        }
        return "R001";
    }

    public boolean save(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "insert into raw_material (material_id, material_name, price, qty) values (?,?,?,?)",
                rawMaterialDTO.getMaterialId(),
                rawMaterialDTO.getMaterialName(),
                rawMaterialDTO.getPrice(),
                rawMaterialDTO.getQty()
        );
    }

    public ArrayList<RawMaterialDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from raw_material");

        ArrayList<RawMaterialDTO> rawMaterialDTOArrayList = new ArrayList<>();
        while (resultSet.next()){
            RawMaterialDTO rawMaterialDTO = new RawMaterialDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
            rawMaterialDTOArrayList.add(rawMaterialDTO);
        }

        return rawMaterialDTOArrayList;
    }

    public boolean update(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update raw_material set material_name=?, price=?, qty=? where material_id=?",
                rawMaterialDTO.getMaterialName(),
                rawMaterialDTO.getPrice(),
                rawMaterialDTO.getQty(),
                rawMaterialDTO.getMaterialId()
        );
    }

    public boolean delete(String rawMaterialId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("delete from raw_material where material_id=?",rawMaterialId);
    }

    public ArrayList<RawMaterialDTO> load() throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT material_id,material_name FROM raw_material");
        ArrayList<RawMaterialDTO> rawMaterialList = new ArrayList<>();
        while (result.next()) {
            RawMaterialDTO rawMaterial = new RawMaterialDTO(
                    result.getString(1),
                    result.getString(2)
            );
            rawMaterialList.add(rawMaterial);
        }
        return rawMaterialList;
    }
}
