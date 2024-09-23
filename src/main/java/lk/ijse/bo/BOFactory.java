package lk.ijse.bo;

import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.ItemBOImpl;
import lk.ijse.bo.custom.impl.OrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)?  boFactory =  new BOFactory():boFactory;

    }

    public enum BOTypes{
        Customer, Item,Orders
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Customer:
                return new CustomerBOImpl();

            case Item:
                return  new ItemBOImpl();

            case Orders:
                return  new OrderBOImpl();
            default:
                return null;
        }
    }
}
