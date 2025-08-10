package lk.ijse.pearlgims.bo.custom;

import lk.ijse.pearlgims.bo.SuperBO;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    public String getNextProductId() throws SQLException, ClassNotFoundException;

    public boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException ;

    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException ;

    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException ;

}
