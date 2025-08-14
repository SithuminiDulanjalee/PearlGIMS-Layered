package lk.ijse.pearlgims.bo;

import lk.ijse.pearlgims.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }
    public enum BOTypes{
        CUSTOMER,
        ORDER,
        ORDER_DETAIL,
        PRODUCT,
        RAW_MATERIAL,
        SUPPLIER,
        INVENTORY,
        PRODUCTION,
        HOME_PAGE
    }
    public SuperBO getBO(BOTypes boType) {
        switch(boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAIL:
                return new OrderDetailBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case RAW_MATERIAL:
                return new RawMaterialBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case PRODUCTION:
                return new ProductionBOImpl();
            case HOME_PAGE:
                return new HomePageBOImpl();
            default:
                return null;
        }
    }

}
