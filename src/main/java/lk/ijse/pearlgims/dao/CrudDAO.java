package lk.ijse.pearlgims.dao;

import lk.ijse.pearlgims.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public String getNextId() throws SQLException, ClassNotFoundException ;

    public boolean save(T customerDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean update(T customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean delete(String customerID) throws SQLException, ClassNotFoundException ;



}
