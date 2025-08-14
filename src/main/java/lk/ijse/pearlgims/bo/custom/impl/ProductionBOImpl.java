package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.ProductionBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.OrderDetailDAO;
import lk.ijse.pearlgims.dao.custom.ProductionDAO;
import lk.ijse.pearlgims.dao.custom.QueryDAO;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dao.custom.impl.ProductionDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.QueryDAOImpl;
import lk.ijse.pearlgims.dto.CustomerDTO;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.entity.Customer;
import lk.ijse.pearlgims.entity.OrderDetail;
import lk.ijse.pearlgims.entity.Production;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionBOImpl implements ProductionBO {
    ProductionDAO productionDAO = (ProductionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCTION);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    RawMaterialDAO rawMaterialDAO = (RawMaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RAW_MATERIAL);
    @Override
    public boolean issue(String materialId, int quantity) throws SQLException, ClassNotFoundException {
        return productionDAO.issue(materialId,quantity);
    }

    @Override
    public String getNextProductionId() {
        try {
            return productionDAO.getNextId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ProductionDTO> getAllProduction() throws SQLException, ClassNotFoundException {
        ArrayList<Production> entity =queryDAO.getAll();
        ArrayList<ProductionDTO> productionDTO=new ArrayList<>();
        for (Production p:entity){
            productionDTO.add(new ProductionDTO(p.getProductionId(),p.getMaterialId(),p.getMaterialName(),p.getQty()));
        }
        return productionDTO;
    }

    @Override
    public ArrayList<OrderDetail> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getAll();
    }

    @Override
    public ArrayList<RawMaterialDTO> loadRawMaterial() throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.load();
    }
}
