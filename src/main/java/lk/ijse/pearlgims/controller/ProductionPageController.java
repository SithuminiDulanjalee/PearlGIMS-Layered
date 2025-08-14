package lk.ijse.pearlgims.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pearlgims.bo.BOFactory;
import lk.ijse.pearlgims.bo.custom.ProductionBO;
import lk.ijse.pearlgims.bo.custom.impl.ProductionBOImpl;
import lk.ijse.pearlgims.dao.custom.OrderDetailDAO;
import lk.ijse.pearlgims.dao.custom.ProductionDAO;
import lk.ijse.pearlgims.dao.custom.QueryDAO;
import lk.ijse.pearlgims.dao.custom.RawMaterialDAO;
import lk.ijse.pearlgims.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.ProductionDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.QueryDAOImpl;
import lk.ijse.pearlgims.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.pearlgims.dto.OrderDetailDTO;
import lk.ijse.pearlgims.dto.ProductionDTO;
import lk.ijse.pearlgims.dto.RawMaterialDTO;
import lk.ijse.pearlgims.dto.tm.OrderDetailTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductionPageController implements Initializable {
    public TableView<OrderDetailTM> tblOrderDetails;
    public TableColumn<OrderDetailTM, String> colOrderId;
    public TableColumn<OrderDetailTM, Date> colOrderDate;
    public TableColumn<OrderDetailTM, String> colCustomerName;
    public TableColumn<OrderDetailTM, Integer> colProductCount;
    public TableColumn<OrderDetailTM, Integer> colTotalProductQuantity;
    public TableColumn<?,?> colAction;

    ProductionBO productionBO = (ProductionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCTION);

    public ComboBox combMatrialId;
    public Label txtDetails;
    public TextField txtTssuesQuentity;

    // production table section
    public TableView productionTable;
    public TableColumn<ProductionDTO,String> colProdnctionId;
    public TableColumn<ProductionDTO,String> colMaterialId;
    public TableColumn<ProductionDTO,String> colMaterialName;
    public TableColumn<ProductionDTO,String> colQuentity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeOrderDetailsTable();
        initializeIssuedInventoryTable();
        reload();
    }

    public void reload(){
        try {

            combMatrialId.getSelectionModel().clearSelection();
            txtTssuesQuentity.clear();

            combMatrialId.setItems(FXCollections.observableArrayList(productionBO.loadRawMaterial().stream().map(RawMaterialDTO::getMaterialId).toList()));
            loadTableData();
            loadTableIssuedInventory();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void initializeOrderDetailsTable(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colProductCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
        colTotalProductQuantity.setCellValueFactory(new PropertyValueFactory<>("totalProductQuantity"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnView"));
    }

    public void initializeIssuedInventoryTable(){
        colProdnctionId.setCellValueFactory(new PropertyValueFactory<>("productionId"));
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colMaterialName.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        colQuentity.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<OrderDetailTM> orderDetailTMS = FXCollections.observableArrayList(productionBO.getAllOrderDetails().stream()
                .map(orderDetailDTO -> new OrderDetailTM(
                        orderDetailDTO.getOrderId(),
                        orderDetailDTO.getOrderDate(),
                        orderDetailDTO.getCustomerName(),
                        orderDetailDTO.getProductCount(),
                        orderDetailDTO.getTotalProductQuantity(),
                        new Button("View")
                ))
                .collect(Collectors.toList())
        );
        tblOrderDetails.setItems(orderDetailTMS);
    }

    public void btnOnActionIssue(ActionEvent actionEvent) {

        String materialId = (String) combMatrialId.getValue();
        int quantity = Integer.parseInt(txtTssuesQuentity.getText());

        try{
            boolean isIssued = productionBO.issue(materialId , quantity);
            if (isIssued){
                reload();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully issued material").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail Issued Material").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void loadTableIssuedInventory() throws SQLException, ClassNotFoundException {
        ArrayList<ProductionDTO> productionDTOS = productionBO.getAllProduction();
        ObservableList<ProductionDTO> productionDTOObservableList = FXCollections.observableArrayList();

        productionDTOObservableList.addAll(productionDTOS);
        productionTable.setItems(productionDTOObservableList);
    }
}
