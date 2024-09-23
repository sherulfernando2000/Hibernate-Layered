package lk.ijse.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.bo.custom.impl.OrderBOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.view.tdm.CustomerTm;
import lk.ijse.view.tdm.OrderDetailsTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnHome1;

    @FXML
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<Integer> cmbItemId;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemDescroption;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    private Label txtCusName;

    @FXML
    private Label txtItemName;

    @FXML
    private Label txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private Label txtQtyOnHand;

    @FXML
    private TextField txtTotal;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;
    @FXML
    private Button btnPlaceOrder;

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Orders);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Item);



    public void initialize(){
        setCellValueFactorty();
        lblOrderId.setText(generateNextOrderID());
        lblDate.setText(LocalDate.now().toString());
        loadAllItemCodes();

    }

    private String generateNextOrderID() {


            String currentId = (String) orderBO.currentId();
            if (currentId != null) {
                int curId = Integer.parseInt(currentId.replace("OID-", "") + 1);
                String nextId =  String.format("OID-%03d",curId) ;
                return nextId;
            }
        return "OID-001";
    }

    private void setCellValueFactorty() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemDescroption.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailsTm, Button> lastCol = (TableColumn<OrderDetailsTm, Button>) colDelete;

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlaceOrder.setDisable(!(cmbCustomerId.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
    }
    private void calculateTotal() {
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/dashboard_form.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event){

    }

    /*private void loadAllCustomerIds() {
        try {
            ArrayList<String> customerIds = customerBO.loadAllCustomerIds();

            for (String id:customerIds) {
                cmbCustomerId.getItems().add(id);
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    private void loadAllItemCodes() {
        try {
            ObservableList<Integer> obList = FXCollections.observableArrayList();

            List<ItemDTO>items = itemBO.getAllItem();

            for (ItemDTO item: items) {
                Integer id = item.getiId();
                obList.add(id);

            }
            cmbItemId.setItems(obList);


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
