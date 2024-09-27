package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(int id);

    List<CustomerDTO> getAllCustomer();

    ArrayList<String> loadAllCustomerIds();

    CustomerDTO searchCustomer(Integer cusId);
}
