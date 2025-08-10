package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dto.OrderDetailDTO;
import lk.ijse.pearlgims.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO {
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException;
}
