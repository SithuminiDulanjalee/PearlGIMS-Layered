package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.OrderDetailBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.OrderDetailDAO;
import lk.ijse.pearlgims.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.pearlgims.dto.OrderDetailDTO;
import lk.ijse.pearlgims.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> entity = orderDetailDAO.getAll();

        ArrayList<OrderDetailDTO> orderDetailDTO = new ArrayList<>();

        for (OrderDetail od : entity) {
            orderDetailDTO.add(new OrderDetailDTO(od.getOrderId(), od.getOrderDate(), od.getCustomerName(), od.getProductCount(), od.getTotalProductQuantity()));
        }

        return orderDetailDTO;
    }
}
