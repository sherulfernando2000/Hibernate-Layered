package lk.ijse.dao;

import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public  boolean save(T entity) throws SQLException, ClassNotFoundException ;

    public  boolean update(T entity) throws SQLException, ClassNotFoundException;

    boolean delete(int id);

    List<T> getAll();
}
