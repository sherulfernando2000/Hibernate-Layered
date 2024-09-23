package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dao.custom.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        Customer,Item,Orders
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Customer:
                return new CustomerDAOImpl();

            case Item:
                return new ItemDAOImpl();

            case Orders:
                return new OrderDAOImpl();
            default:
                return null;
        }
    }
}
