/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailDAO {

    @Override
    public boolean save(List<OrderDetails> orderDetailsList,Session session) throws SQLException, ClassNotFoundException {

        try {
            for (OrderDetails orderDetails:orderDetailsList){
                boolean issaved = save(orderDetails,session);
            }
            return true;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public boolean save(OrderDetails entity, Session session) throws SQLException, ClassNotFoundException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
        session.save(entity);
//        session.close();
        return true;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<OrderDetails> getAll() {
        return List.of();
    }

    @Override
    public OrderDetails search(Integer id) {
        return null;
    }
}
