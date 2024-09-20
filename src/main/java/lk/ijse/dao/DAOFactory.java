package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        Customer,EMPLOYEE,INGREDIENT,PRODUCT,ORDER,ORDER_PRODUCT_DETAILS,PAYMENT, SUPPLIER,SUPPLIER_ORDER,QUERY,PRODUCT_EMPLOYEE,USER,WASTE
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Customer:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
