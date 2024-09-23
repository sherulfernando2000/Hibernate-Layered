package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.view.tdm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private AnchorPane rootNode;


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTele;

    @FXML
    private TableView<CustomerTm> tblCustomer;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;


    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);

    public void initialize(){
        setCellValueFactory();
        loadAllCustomers();

    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomer();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getTele(),
                        customer.getAddress()
                );

                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int customerId = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        CustomerDTO customerDTO = new CustomerDTO(customerId,name,address,tel);


        try {
            boolean isDeleted = customerBO.deleteCustomer(customerId);
            if (isDeleted = true ) {
                new Alert(Alert.AlertType.CONFIRMATION,"customer deleted").show();
                loadAllCustomers();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int customerId = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        CustomerDTO customerDTO = new CustomerDTO(customerId,name,address,tel);


        try {
            boolean isSaved = customerBO.saveCustomer(customerDTO);
            if (isSaved ) {
                new Alert(Alert.AlertType.CONFIRMATION,"customer saved").show();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int customerId = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        CustomerDTO customerDTO = new CustomerDTO(customerId,name,address,tel);


        try {
            boolean isUpdated = customerBO.updateCustomer(customerDTO);
            if (isUpdated = true ) {
                new Alert(Alert.AlertType.CONFIRMATION,"customer updated").show();
                loadAllCustomers();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/dashboard_form.fxml"));
        Scene scene =  new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Form");

    }
}
