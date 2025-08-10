package lk.ijse.pearlgims.bo.custom.impl;

import lk.ijse.pearlgims.bo.custom.ProductBO;
import lk.ijse.pearlgims.dao.DAOFactory;
import lk.ijse.pearlgims.dao.custom.ProductDAO;
import lk.ijse.pearlgims.dao.custom.impl.ProductDAOImpl;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.ProductDTO;
import lk.ijse.pearlgims.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO  productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public String getNextProductId() throws SQLException, ClassNotFoundException {
        return productDAO.getNextId();
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getPrice(),productDTO.getQty(),productDTO.getStatus(),productDTO.getSize()));
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> entity = productDAO.getAll();
        ArrayList<ProductDTO> productDTO = new ArrayList<>();
        for (Product product : entity) {
            productDTO.add(new ProductDTO(product.getProductId(),product.getName(),product.getPrice(),product.getQty(),product.getStatus(),product.getSize()));
        }
        return productDTO;
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getPrice(),productDTO.getQty(),productDTO.getStatus(),productDTO.getSize()));
    }

    @Override
    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }
}
