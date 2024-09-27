/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.dao.custom;

import jakarta.persistence.criteria.Order;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Orders;
import org.hibernate.Session;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders> {
    public Object currentId();

    public boolean save(Orders entity, Session session) throws SQLException, ClassNotFoundException;
}
