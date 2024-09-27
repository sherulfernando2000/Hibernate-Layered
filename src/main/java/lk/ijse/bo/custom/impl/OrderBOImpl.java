/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetails;
import lk.ijse.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Orders);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.OrderDetails);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Item);
    public Object currentId() {

        return orderDAO.currentId();
    }

    @Override
    public void placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        boolean isUpdated =false;

        try{

           Orders order = new Orders(orderDTO.getoId(),orderDTO.getDate(),orderDTO.getCustomer());
           System.out.println(order);
           boolean isSaved = orderDAO.save(order,session);
           if(isSaved){
               List<OrderDetails> orderDetails1 = new ArrayList<>();
               for (OrderDetailDTO orderDetail: orderDetails ){
                   Item item1 = new Item();
                   item1.setiId(orderDetail.getiId());
                   System.out.println(item1);

                    isUpdated = itemDAO.updateQty(item1,orderDetail.getQty(),session);

                   orderDetails1.add(new OrderDetails(order,item1,orderDetail.getQty()));
               }

                       if (isUpdated) {
                           orderDetailDAO.save(orderDetails1,session);
                           transaction.commit();
                           new Alert(Alert.AlertType.CONFIRMATION,"transaction completed").show();

                       }


           }



       }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                transaction.rollback();
       }finally {
            session.close();
        }



    }

    @Override
    public void saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Orders order = new Orders(orderDTO.getoId(),orderDTO.getDate(),orderDTO.getCustomer());
        orderDAO.save(order);
    }
}
