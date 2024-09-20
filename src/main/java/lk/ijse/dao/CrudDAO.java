package lk.ijse.dao;

import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO {
    public  boolean save(T entity) throws SQLException, ClassNotFoundException ;

    public  boolean update(T entity) throws SQLException, ClassNotFoundException;
}
