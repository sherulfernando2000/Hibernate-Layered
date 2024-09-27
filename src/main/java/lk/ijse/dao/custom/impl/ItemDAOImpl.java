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
import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {




    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }
    }


    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        try{
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
           transaction.commit();
            return true;

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Item WHERE iId = ?1");
            query.setParameter(1,id);
            query.executeUpdate();
            transaction.commit();
            session.close();
            return  true;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            return false;
        }

    }

    @Override
    public List<Item> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("from Item");
            List<Item> items = query.list();
        Transaction transaction = session.beginTransaction();
            transaction.commit();
            //session.close();
            return items;

    }

    @Override
    public Item search(Integer id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from Item where iId = ?1");
        query.setParameter(1, id);
        Item item = (Item) query.uniqueResult();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        //session.close();
        return item;
    }


    @Override
    public ArrayList<ItemDTO> loadAllItemCodes() {
        /*Query query = session.createQuery("select iId from Item");
        List<Item> items = query.list();*/
        return null;

    }

    @Override
    public boolean updateQty(Item entity, double qty1, Session session) throws SQLException, ClassNotFoundException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
        try{
            System.out.println(qty1);
            Query query = session.createQuery("update Item set qty = qty - ?1 where iId = ?2");
            query.setParameter(1,qty1);
            query.setParameter(2,entity.getiId());

            int result = query.executeUpdate();

            // Commit the transaction if the update was successful
            if (result > 0) {
//               transaction.commit();
                return true;
            }

            return true;

        } catch (Exception e) {
            // Rollback transaction if there was an issue
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update item quantity: " + e.getMessage()).show();
            throw e;
        }
    }

            /*transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }*/
}

