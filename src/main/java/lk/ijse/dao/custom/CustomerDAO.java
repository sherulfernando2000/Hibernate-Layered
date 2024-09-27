package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    boolean save(Customer customer);

    boolean update(Customer customer);

    boolean delete(int id);

    List<Customer> getAll();


}
