package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Customer);

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getTele(),customer.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) {
        return customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getTele(), customer.getAddress()));
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerDAO.delete(id);

    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers =  customerDAO.getAll();
        for (Customer customer: customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(), customer.getAddress(), customer.getTele()));
        }
        return customerDTOS;
    }
}
