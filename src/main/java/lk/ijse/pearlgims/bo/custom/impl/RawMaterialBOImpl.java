package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.RawMaterialBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.pearlgims.dto.RawMaterialDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RawMaterialBOImpl implements RawMaterialBO {
    RawMaterialDAO rawMaterialDAO = (RawMaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RAW_MATERIAL);
    @Override
    public String getNextRawMaterialId() throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.getNextId();
    }

    @Override
    public boolean saveRawMaterial(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.save(rawMaterialDTO);
    }

    @Override
    public ArrayList<RawMaterialDTO> getAllRawMaterial() throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.getAll();
    }

    @Override
    public boolean updateRawMaterial(RawMaterialDTO rawMaterialDTO) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.update(rawMaterialDTO);
    }

    @Override
    public boolean deleteRawMaterial(String rawMaterialId) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.delete(rawMaterialId);
    }
}
