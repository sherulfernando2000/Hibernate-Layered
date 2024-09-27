/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    public Object currentId(){
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            Query query = session.createQuery("select oId from Orders ORDER BY oId DESC LIMIT 1");
            Object currentId = query.uniqueResult();
            return currentId;
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public boolean save(Orders entity, Session session) throws SQLException, ClassNotFoundException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();

       /* try {
            System.out.println("createquery");
            Query query = session.createQuery("insert into Orders(oId,date,customer) values(?1,?2, ?3)");
            session.save(entity);
            *//*query.setParameter(1,entity.getoId());
            query.setParameter(2,entity.getDate());
            query.setParameter(3,entity.getCustomer());
            query.executeUpdate();
            System.out.println("execute update");
            session.close();*//*
            return true;
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            return false;
        }*/

        try {
            session.save(entity);
            return true;
        } catch (Exception e) {
//            transaction.rollback();
            throw e;
        }


        //

    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) {

        return false;
    }

    @Override
    public List<Orders> getAll() {
        return List.of();
    }

    @Override
    public Orders search(Integer id) {
        return null;
    }
}
