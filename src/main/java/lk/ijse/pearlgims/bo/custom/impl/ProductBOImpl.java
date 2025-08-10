package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.ProductBO;
import lk.ijse.pearlgims.dao.custom.ProductDAO;
import lk.ijse.pearlgims.dao.custom.impl.ProductDAOImpl;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO  productDAO = new ProductDAOImpl();
    @Override
    public String getNextProductId() throws SQLException, ClassNotFoundException {
        return productDAO.getNextId();
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(productDTO);
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        return productDAO.getAll();
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(productDTO);
    }

    @Override
    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }
}
