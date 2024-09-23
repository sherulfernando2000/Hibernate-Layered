/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Item);
    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        return itemDAO.save(new Item(itemDTO.getiId(),itemDTO.getiName(),itemDTO.getPrice(),itemDTO.getQty()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
            return itemDAO.update(new Item(itemDTO.getiId(),itemDTO.getiName(),itemDTO.getPrice(),itemDTO.getQty()));
    }

    @Override
    public boolean deleteItem(int id) {
            return itemDAO.delete(id);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> items = itemDAO.getAll();
        for (Item item: items){
            ItemDTO itemDTO = new ItemDTO(item.getiId(),item.getiName(),item.getPrice(),item.getQty());
            itemDTOS.add(itemDTO);
        }

        return itemDTOS;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItemCodes() {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> items = itemDAO.getAll();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getiId(), item.getiName(), item.getPrice(), item.getQty()));
        }
        return itemDTOS;
    }
}
