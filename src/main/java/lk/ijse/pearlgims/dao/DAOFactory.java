package lk.ijse.pearlgims.dao;

import lk.ijse.pearlgims.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return (daoFactory==null)?new DAOFactory():daoFactory;
    }
    public enum DAOTypes {
        CUSTOMER,
        ORDER,
        ORDER_DETAIL,
        ORDER_ITEM,
        PRODUCT,
        RAW_MATERIAL,
        SUPPLIER
    }
    public SuperDAO getDAO(DAOTypes daoType) {
        switch(daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case ORDER_ITEM:
                return new OrderItemDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case RAW_MATERIAL:
                return new RawMaterialDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}