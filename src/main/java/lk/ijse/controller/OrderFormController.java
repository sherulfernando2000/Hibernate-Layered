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
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.OrderDetails;
import lk.ijse.view.tdm.CustomerTm;
import lk.ijse.view.tdm.OrderDetailsTm;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFormController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnHome1;

    @FXML
    private ComboBox<Integer> cmbCustomerId;

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
    private TableView<OrderDetailsTm> tblOrderDetails;

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

    @FXML
    private Button btnSave;

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Orders);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Item);


    public void initialize() {
        setCellValueFactorty();
        lblOrderId.setText(generateNextOrderID());
        lblDate.setText(LocalDate.now().toString());
        loadAllItemCodes();
        loadAllCustomerCodes();

    }

    private void loadAllCustomerCodes() {
        try {
            ObservableList<Integer> obList = FXCollections.observableArrayList();

            List<CustomerDTO> customers = customerBO.getAllCustomer();

            for (CustomerDTO customer : customers) {
                Integer id = customer.getId();
                obList.add(id);

            }
            cmbCustomerId.setItems(obList);


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    private String generateNextOrderID() {


        String currentId = (String) orderBO.currentId();
        System.out.println(currentId);
        if (currentId != null) {
            int curId = Integer.parseInt(currentId.replace("OID-", "") )+ 1;
            String nextId = String.format("OID-%03d", curId);
            return nextId;
        }else{
            return "OID-001";
        }
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
        double total =0;
            for(OrderDetailsTm orderDetailsTm : tblOrderDetails.getItems()){
                     total += orderDetailsTm.getTotal();
            }
        txtTotal.setText(String.valueOf(total));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if(!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText())<=0 ||
        Integer.parseInt(txtQty.getText())>Double.parseDouble(txtQtyOnHand.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();

        }

        int customerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        String customerName = txtCusName.getText();

        int itemCode = cmbItemId.getSelectionModel().getSelectedItem();
        String itemName = txtItemName.getText();
        double itemPrice = Double.parseDouble(txtPrice.getText());
        double qty = Double.parseDouble(txtQty.getText());
        double total = itemPrice * qty;

        boolean exists = tblOrderDetails.getItems().stream().anyMatch(detail -> detail.getCode().equals(String.valueOf(itemCode)));

        if (exists){
            OrderDetailsTm orderDetailTM = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(String.valueOf(itemCode))).findFirst().get();
            if (btnSave.getText().equalsIgnoreCase("Update")) {
                orderDetailTM.setQty((int) qty);
                orderDetailTM.setTotal(total);
                tblOrderDetails.getSelectionModel().clearSelection();
            }else{
                orderDetailTM.setQty((int) (orderDetailTM.getQty() + qty));
                total = itemPrice * qty;
                orderDetailTM.setTotal(total);
            }
            tblOrderDetails.refresh();
        }else{
            tblOrderDetails.getItems().add(new OrderDetailsTm(String.valueOf(itemCode), itemName,(int)qty, itemPrice, total));
        }
        calculateTotal();
        //cmbItemId.getSelectionModel().clearSelection();
       cmbItemId.requestFocus();

        enableOrDisablePlaceOrderButton();


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
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        List<OrderDetailDTO> orderDetails = tblOrderDetails.getItems().stream().map(tm -> new OrderDetailDTO(lblOrderId.getText(), Integer.parseInt(tm.getCode()), tm.getQty())).collect(Collectors.toList());
        for (OrderDetailDTO odd : orderDetails){
            System.out.println(odd);
        }

        Customer customer = new Customer();
        customer.setId(cmbCustomerId.getValue());
        customer.setName(txtCusName.getText());
        customer.setAddress("galle");
        customer.setTele("0718307738");
        OrderDTO orderDTO = new OrderDTO(lblOrderId.getText(),customer, LocalDate.now());

        orderBO.placeOrder(orderDTO,orderDetails);


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

            List<ItemDTO> items = itemBO.getAllItem();

            for (ItemDTO item : items) {
                Integer id = item.getiId();
                obList.add(id);

            }
            cmbItemId.setItems(obList);


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        Integer cusId = cmbCustomerId.getSelectionModel().getSelectedItem();
        CustomerDTO customer = customerBO.searchCustomer(cusId);
        txtCusName.setText(customer.getName());

    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
            Integer itemId = cmbItemId.getSelectionModel().getSelectedItem();
            ItemDTO itemDTO = itemBO.searchItem(itemId);
            txtItemName.setText(itemDTO.getiName());
            txtPrice.setText(String.valueOf(itemDTO.getPrice()));
            txtQtyOnHand.setText(String.valueOf(itemDTO.getQty()));


    }
}


