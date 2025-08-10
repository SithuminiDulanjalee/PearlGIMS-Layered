package lk.ijse.pearlgims.dao.custom;

import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllSizes() throws SQLException, ClassNotFoundException;

    public ProductDTO findById(String selectedProductId) throws SQLException, ClassNotFoundException;

    public boolean reduceQty(OrderItemDTO orderItemDTO) throws SQLException, ClassNotFoundException ;


    public String getNextId() throws SQLException, ClassNotFoundException;

    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException ;

    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String productId) throws SQLException, ClassNotFoundException ;
}
