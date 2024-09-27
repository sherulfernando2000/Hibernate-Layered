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
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.impl.ItemBOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.view.tdm.ItemTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<ItemTm> tblCustomer;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Item);


    public void initialize(){
        setCellValueFactoy();
        loadAllItem();
    }

    private void setCellValueFactoy(){
        colId.setCellValueFactory(new PropertyValueFactory<>("iId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("iName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void loadAllItem(){
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        List<ItemDTO> itemDTOS = itemBO.getAllItem();
        for (ItemDTO itemDTO: itemDTOS){
            ItemTm itemTm = new ItemTm(
                    itemDTO.getiId(),
                    itemDTO.getiName(),
                    itemDTO.getPrice(),
                    itemDTO.getQty()
            );
            obList.add(itemTm);
        }
          for (ItemTm itemtm: obList){
              System.out.println(itemtm.toString());
          }
          tblCustomer.setItems(obList);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
            int id = Integer.parseInt(txtId.getText());

            boolean isDeleted = itemBO.deleteItem(id);
        if (isDeleted == true) {
            new Alert(Alert.AlertType.CONFIRMATION,"Item deleted successfully.");
        }else{
            new Alert(Alert.AlertType.ERROR,"item not deleted.");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        double price = (Double.parseDouble(txtPrice.getText()));
        double qty = (Double.parseDouble(txtQty.getText()));

        ItemDTO itemDTO = new ItemDTO(id,name,price,qty);



        boolean isSaved = itemBO.saveItem(itemDTO);
            if (isSaved == true) {
                new Alert(Alert.AlertType.CONFIRMATION,"item saved succesfully").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"item not saved succesfully").show();
            }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        int iId = Integer.parseInt(txtId.getText());
        String iName = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        double qty = Double.parseDouble(txtQty.getText());

        ItemDTO itemDTO = new ItemDTO(iId,iName,price,qty);

        boolean isUpdated = itemBO.updateItem(itemDTO);
        loadAllItem();
        if (isUpdated == true) {
            new Alert(Alert.AlertType.CONFIRMATION,"Item updated").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"item not updated.");
        }

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/dashboard_form.fxml"));
        Scene scene =  new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Form");

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }}
