package lk.ijse.pearlgims.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pearlgims.bo.BOFactory;
import lk.ijse.pearlgims.bo.custom.InventoryBO;
import lk.ijse.pearlgims.bo.custom.impl.InventoryBOImpl;
import lk.ijse.pearlgims.dao.custom.InventoryDAO;
import lk.ijse.pearlgims.dao.custom.InventoryDetailDAO;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dao.custom.SupplierDAO;
import lk.ijse.pearlgims.dao.custom.impl.InventoryDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.InventoryDetailDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.dto.SupplierDTO;
import lk.ijse.pearlgims.dto.tm.InventoryDetailsTm;
import lk.ijse.pearlgims.dto.tm.InventoryTM;
//import lk.ijse.pearlgims.model.InventoryDetailModel;
//import lk.ijse.pearlgims.model.InventoryModel;
//import lk.ijse.pearlgims.model.RawMaterialModel;
//import lk.ijse.pearlgims.model.SupplierModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InventoryPageController implements Initializable {

    // inventory section
    public TableView<InventoryTM> tblInventory;
    public TableColumn<InventoryTM,String> colInventrId1;
    public TableColumn<InventoryTM,String> colSupplierId;
    public TableColumn<InventoryTM,String> conInventryDate;
    public TableColumn<InventoryTM,String> colInventotyName;

//    inventory details section
    public TableView<InventoryDetailsTm> tblInventoryDetails;
    public TableColumn<InventoryDetailsTm,String> colTBInventryId;
    public TableColumn<InventoryDetailsTm,String> colTBMaterialId;
    public TableColumn<InventoryDetailsTm,String> colTBQty;
    public ComboBox cmbSupplier;
    public TextField txtInventryName;
    public DatePicker dateInventriDate;
    public ComboBox combInventry;
    public ComboBox combRowMaterial;
    public TextField txtQuentity;

//    private InventoryModel inventoryModel = new InventoryModel();
//    private InventoryDetailModel inventoryDetailModel = new InventoryDetailModel();
//    private SupplierModel supplierModel = new SupplierModel();
//    private RawMaterialModel rawMaterialModel = new RawMaterialModel();

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INVENTORY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeInventoryTable();
        initializeInventoryDetailsTable();
        reload();
    }


    public void initializeInventoryTable(){
        colInventrId1.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("name"));
        conInventryDate.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colInventotyName.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void initializeInventoryDetailsTable(){
        colTBInventryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        colTBMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colTBQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void loadInventoryTable() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryTM> inventotyDTOArrayList = inventoryBO.getAllInventory();
        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();

        for (InventoryTM inventoryTM : inventotyDTOArrayList){
            inventoryTMS.add(inventoryTM);
        }
        tblInventory.setItems(inventoryTMS);
    }

    public void loadInventoryDetailsTable() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDetailsTm> inventotyDetailsDTOArrayList = inventoryBO.getAllInventoryDetaills();
        ObservableList<InventoryDetailsTm> inventoryDetailsTms = FXCollections.observableArrayList();

        for (InventoryDetailsTm  inventoryDetailsTm: inventotyDetailsDTOArrayList){
            inventoryDetailsTms.add(inventoryDetailsTm);
        }
        tblInventoryDetails.setItems(inventoryDetailsTms);
    }

    public void reload(){

        try {
            loadInventoryTable();
            loadInventoryDetailsTable();

            // clear combo box
            cmbSupplier.getSelectionModel().clearSelection();
            combInventry.getSelectionModel().clearSelection();
            combRowMaterial.getSelectionModel().clearSelection();

            // clear text fields
            txtInventryName.clear();
            txtQuentity.clear();
            dateInventriDate.cancelEdit();

            cmbSupplier.setItems(FXCollections.observableArrayList(inventoryBO.loadSupplier().stream().map(SupplierDTO::getSupplierID).toList()));
            combInventry.setItems(FXCollections.observableArrayList(inventoryBO.loadInventory().stream().map(InventoryTM::getInventoryId).toList()));
            combRowMaterial.setItems(FXCollections.observableArrayList(inventoryBO.loadRawMaterial().stream().map(RawMaterialDTO::getMaterialId).toList()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
    
    public void btnAddInventoryOnAction(ActionEvent actionEvent) {
        String supplierId = (String) cmbSupplier.getValue();
        String name = txtInventryName.getText();
        String date = dateInventriDate.getValue().toString();

        InventoryTM inventoryTM = new InventoryTM(
                inventoryBO.getNextInventoryId(),
                name,
                supplierId,
                Date.valueOf(date)
        );

        try {
            boolean isSaved = inventoryBO.saveInventory(inventoryTM);
            if (isSaved) {
                reload();
                new Alert(Alert.AlertType.INFORMATION, "Inventory saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save inventory").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    // inventory details
    public void btnAddItemToInventory(ActionEvent actionEvent) {
        String inventoryId = (String) combInventry.getValue();
        String materialId = (String) combRowMaterial.getValue();
        String qty = txtQuentity.getText();

        InventoryDetailsTm inventoryDetailsTm = new InventoryDetailsTm(
                inventoryBO.getNextInventoryDetailsId(),
                inventoryId,
                materialId,
                Integer.parseInt(qty)
        );

        try{
            boolean isSaved = inventoryBO.saveInventoryDetails(inventoryDetailsTm);
            if (isSaved) {
                reload();
                new Alert(Alert.AlertType.INFORMATION, "Inventory details saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save inventory details").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
}
