package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object issave =  session.save(entity);
        if (issave != null) {
            transaction.commit();
            session.close();
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Customer entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int id) {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("delete from Customer where id = ?1");
       query.setParameter(1, id);
       boolean isDelete = query.executeUpdate() > 0;

        if (isDelete ) {
            transaction.commit();
            session.close();
            return true;
        }
       return false;
    }

    @Override
    public List<Customer> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Customer");
        List<Customer> customer = query.list();
        return customer;
    }

    @Override
    public Customer search(Integer id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction trancation = session.beginTransaction();

        Query query = session.createQuery("from Customer where id = ?1");
        query.setParameter(1,id);
        Customer customer = (Customer)query.uniqueResult();
        trancation.commit();
        //session.close();
        return customer;

    }


}
