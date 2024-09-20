package lk.ijse.bo;

import lk.ijse.bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)?  boFactory =  new BOFactory():boFactory;

    }

    public enum BOTypes{
        Customer
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Customer:
                return new CustomerBOImpl();

            default:
                return null;
        }
    }
}
