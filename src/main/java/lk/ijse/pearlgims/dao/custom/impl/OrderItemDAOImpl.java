package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.custom.OrderItemDAO;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDAOImpl implements OrderItemDAO {
    private ProductDAOImpl productDAO = new ProductDAOImpl();
    @Override
    public boolean saveDetailsList(ArrayList<OrderItemDTO> orderItemList) throws SQLException, ClassNotFoundException {
        for (OrderItemDTO orderItemDTO : orderItemList) {
            boolean isOrderDetailsSaved = saveDetail(orderItemDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = productDAO.reduceQty(orderItemDTO);
            if (!isItemUpdated) {
                return false;
            }


        }
        return true;
    }

    public boolean saveDetail(OrderItemDTO orderItemDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into order_item values (?,?,?,?)",
                orderItemDTO.getOrderId(),
                orderItemDTO.getProductId(),
                orderItemDTO.getQty(),
                orderItemDTO.getPrice()
        );
    }
}
