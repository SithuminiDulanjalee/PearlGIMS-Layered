package lk.ijse.pearlgims.dao.custom.impl;

import lk.ijse.pearlgims.dao.SQLUtil;
import lk.ijse.pearlgims.dao.custom.ProductDAO;
import lk.ijse.pearlgims.dto.ProductDTO;
import lk.ijse.pearlgims.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select product_id from product");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }

    public ArrayList<String> getAllSizes() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select distinct size from product");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            String size = rst.getString(1);
            list.add(size);
        }
        return list;
    }

    public Product findById(String selectedProductId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery(
                "select * from product where product_id=?",
                selectedProductId
        );
        if (rst.next()) {
            return new Product(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }


    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select product_id from product order by product_id desc limit 1");

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber+1;

            String nextIdString = String.format("P%03d",nextIdNumber);
            return nextIdString;
        }
        return "P001";
    }

    public boolean save(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "insert into product (product_id, name, price, qty, status, size) values (?,?,?,?,?,?)",
                entity.getProductId(),
                entity.getName(),
                entity.getPrice(),
                entity.getQty(),
                entity.getStatus(),
                entity.getSize()
        );
    }

    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from product");

        ArrayList<Product> products = new ArrayList<>();
        while (resultSet.next()){
            Product entity = new Product(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6));
            products.add(entity);
        }

        return products;
    }

    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update product set name=?, price=?, qty=?, status=?, size=? where product_id=?",
                entity.getName(),
                entity.getPrice(),
                entity.getQty(),
                entity.getStatus(),
                entity.getSize(),
                entity.getProductId()
        );
    }

    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("delete from product where product_id=?",productId);
    }
}
