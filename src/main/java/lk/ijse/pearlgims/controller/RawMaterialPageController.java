package lk.ijse.pearlgims.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.pearlgims.bo.BOFactory;
import lk.ijse.pearlgims.bo.custom.RawMaterialBO;
import lk.ijse.pearlgims.bo.custom.impl.RawMaterialBOImpl;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.dto.tm.RawMaterialTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RawMaterialPageController implements Initializable {
    public TextField txtSearch;
    public TableView<RawMaterialTM> tblRawMaterial;
    public TableColumn<RawMaterialTM, String> colMaterialId;
    public TableColumn<RawMaterialTM, String> colName;
    public TableColumn<RawMaterialTM, Double> colPrice;
    public TableColumn<RawMaterialTM, Integer> colQty;
    public Label lblMaterialId;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtQty;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReport;
    public Button btnReset;

//    private final RawMaterialModel rawMaterialModel = new RawMaterialModel();

    RawMaterialBO rawMaterialBO = (RawMaterialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RAW_MATERIAL);

    public void txtSearchBarOnAction(KeyEvent keyEvent) {
        String searchQuery = txtSearch.getText().trim();
        try {
            if (searchQuery.isEmpty()) {
                loadTableData();
            } else {
                loadFilteredData(searchQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Search failed").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchQuery = txtSearch.getText().trim();
        try {
            if (searchQuery.isEmpty()) {
                loadTableData();
            } else {
                loadFilteredData(searchQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Search failed").show();
        }
    }

    public void onClickRawMaterialTable(MouseEvent mouseEvent) {
        RawMaterialTM selectedItem = tblRawMaterial.getSelectionModel().getSelectedItem();

        if(selectedItem != null){
            lblMaterialId.setText(selectedItem.getMaterialId());
            txtName.setText(selectedItem.getMaterialName());
            txtPrice.setText(String.valueOf(selectedItem.getPrice()));
            txtQty.setText(String.valueOf(selectedItem.getQty()));
        }

        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String materialId = lblMaterialId.getText();
        String name = txtName.getText();
        String priceDouble = txtPrice.getText();
        double price = Double.parseDouble(priceDouble);
        String quantity = txtQty.getText();
        int qty = Integer.parseInt(quantity);

        RawMaterialDTO rawMaterialDTO = new RawMaterialDTO(
                materialId,
                name,
                price,
                qty
        );

        try {
            boolean isSaved = rawMaterialBO.saveRawMaterial(rawMaterialDTO);

            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Raw material saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save raw material").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String materialIdUpdate = lblMaterialId.getText();
        String nameUpdate = txtName.getText();
        String priceDoubleUpdate = txtPrice.getText();
        double priceUpdate = Double.parseDouble(priceDoubleUpdate);
        String quantityUpdate = txtQty.getText();
        int qtyUpdate = Integer.parseInt(quantityUpdate);

        RawMaterialDTO rawMaterialDTO = new RawMaterialDTO(
                materialIdUpdate,
                nameUpdate,
                priceUpdate,
                qtyUpdate
        );
        try {
            boolean isUpdated = rawMaterialBO.updateRawMaterial(rawMaterialDTO);

            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Raw material updated successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update raw material").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail"+e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String materialID = lblMaterialId.getText();
            try {
                boolean isDeleted = rawMaterialBO.deleteRawMaterial(materialID);

                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Raw material deleted successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete raw material").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail").show();
            }
        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try{
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<RawMaterialDTO> rawMaterialDTOArrayList = rawMaterialBO.getAllRawMaterial();
        ObservableList<RawMaterialTM> rawMaterialTMS = FXCollections.observableArrayList();

        for (RawMaterialDTO rawMaterialDTO : rawMaterialDTOArrayList) {
            RawMaterialTM rawMaterialTM = new RawMaterialTM(
                    rawMaterialDTO.getMaterialId(),
                    rawMaterialDTO.getMaterialName(),
                    rawMaterialDTO.getPrice(),
                    rawMaterialDTO.getQty());
            rawMaterialTMS.add(rawMaterialTM);
        }
        tblRawMaterial.setItems(rawMaterialTMS);

    }

    private void loadFilteredData(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<RawMaterialDTO> rawMaterialDTOArrayList = rawMaterialBO.getAllRawMaterial();
        ObservableList<RawMaterialTM> filteredList = FXCollections.observableArrayList();

        for (RawMaterialDTO rawMaterialDTO : rawMaterialDTOArrayList) {
            if (rawMaterialDTO.getMaterialId().toLowerCase().contains(searchText.toLowerCase()) ||
                    rawMaterialDTO.getMaterialName().toLowerCase().contains(searchText.toLowerCase()) ||
                    String.valueOf(rawMaterialDTO.getPrice()).contains(searchText) ||
                    String.valueOf(rawMaterialDTO.getQty()).contains(searchText)) {

                filteredList.add(new RawMaterialTM(
                        rawMaterialDTO.getMaterialId(),
                        rawMaterialDTO.getMaterialName(),
                        rawMaterialDTO.getPrice(),
                        rawMaterialDTO.getQty()
                ));
            }
        }

        tblRawMaterial.setItems(filteredList);
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = rawMaterialBO.getNextRawMaterialId();
        lblMaterialId.setText(nextId);
    }

    private void resetPage(){
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.clear();
            txtPrice.clear();
            txtQty.clear();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Somthing went wrong.."+e.getMessage()).show();
        }
    }
}