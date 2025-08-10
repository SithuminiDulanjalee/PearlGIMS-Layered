package lk.ijse.pearlgims.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.pearlgims.bo.BOFactory;
import lk.ijse.pearlgims.bo.custom.OrderBO;
import lk.ijse.pearlgims.bo.custom.impl.OrderBOImpl;
import lk.ijse.pearlgims.dao.custom.*;
import lk.ijse.pearlgims.dao.custom.impl.*;
import lk.ijse.pearlgims.db.DBConnection;
import lk.ijse.pearlgims.dto.OrderItemDTO;
import lk.ijse.pearlgims.dto.OrdersDTO;
import lk.ijse.pearlgims.dto.ProductDTO;
import lk.ijse.pearlgims.dto.tm.OrderTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {
    public TextField txtSearch;
    public TableView<OrderTM> tblOrders;
    public TableColumn<OrderTM,String> colProductId;
    public TableColumn<OrderTM,String> colProductName;
    public TableColumn<OrderTM,Integer> colQty;
    public TableColumn<OrderTM,Double> colPrice;
    public TableColumn<OrderTM,Double> colTotal;
    public TableColumn<?,?> colAction;
    public Label lblOrderId;
    public Label lblOrderDate;
    public ComboBox<String> cmbCustomerId;
    public ComboBox<String> cmbProductId;
    public Label lblProductName;
    public ComboBox<String> cmbSize;
    public Label lblQtyOnInventory;
    public Label lblPrice;
    public TextField txtAddToOrdersQty;
    public Label lblCustomerName;

//    private final OrdersModel ordersModel = new OrdersModel();
//    private final CustomerModel customerModel = new CustomerModel();
//    private final ProductModel productModel = new ProductModel();

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    private final static ObservableList<OrderTM> orderData = FXCollections.observableArrayList();
    public TableColumn<OrderTM,String> colSize;

    public void txtSearchBarOnAction(KeyEvent keyEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        String selectedCustomerName = orderBO.findNameByCustomerId(selectedCustomerId);

        if (selectedCustomerName != null) {
            lblCustomerName.setText(selectedCustomerName);
        } else {
            lblCustomerName.setText("");
        }
    }

    public void cmbProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String selectedProductId = cmbProductId.getSelectionModel().getSelectedItem();
        ProductDTO productDTO = orderBO.findByProductId(selectedProductId);

        if (productDTO != null) {
            lblProductName.setText(productDTO.getName());
            lblPrice.setText(String.valueOf(productDTO.getPrice()));
            lblQtyOnInventory.setText(String.valueOf(productDTO.getQty()));
        } else {
            lblProductName.setText("");
            lblQtyOnInventory.setText("");
            lblPrice.setText("");
        }
    }

    public void cmdSizeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String selectedSize = cmbSize.getSelectionModel().getSelectedItem();
        ProductDTO productDTO = orderBO.findByProductId(selectedSize);
    }

    public void btnAddToOrdersOnAction(ActionEvent actionEvent) {
        String selectedProductId = cmbProductId.getSelectionModel().getSelectedItem();
        String selectedSize = cmbSize.getSelectionModel().getSelectedItem();
        String orderItemQtyString = txtAddToOrdersQty.getText();

        if (selectedProductId == null) {
            new Alert(Alert.AlertType.WARNING, "Please select product..!").show();
            return;
        }

        if (!orderItemQtyString.matches("^[0-9]+$")) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid quantity..!").show();
            return;
        }

        String productQtyOnInventoryString = lblQtyOnInventory.getText();

        int orderItemQty = Integer.parseInt(orderItemQtyString);
        int productQtyOnInventory = Integer.parseInt(productQtyOnInventoryString);

        if (productQtyOnInventory < orderItemQty) {
            new Alert(Alert.AlertType.WARNING, "Not enough product quantity..!").show();
            return;
        }

        String productName = lblProductName.getText();
        String productPriceString = lblPrice.getText();

        double productPrice = Double.parseDouble(productPriceString);
        double total = productPrice * orderItemQty;


        for (OrderTM orderTM : orderData) {
            if (orderTM.getProductId().equals(selectedProductId)) {
                int newQty = orderTM.getOrderQty() + orderItemQty;

                if (productQtyOnInventory < newQty) {
                    new Alert(Alert.AlertType.WARNING, "Not enough product quantity..!").show();
                    return;
                }
                txtAddToOrdersQty.setText("");
                orderTM.setOrderQty(newQty);
                orderTM.setTotal(newQty * productPrice);

                tblOrders.refresh();
                return;
            }
        }

        Button removeBtn = new Button("Remove");
        OrderTM orderTM = new OrderTM(
                selectedProductId,
                productName,
                orderItemQty,
                selectedSize,
                productPrice,
                total,
                removeBtn
        );

        removeBtn.setOnAction(action -> {
            orderData.remove(orderTM);

            tblOrders.refresh();
        });

        txtAddToOrdersQty.setText("");
        orderData.add(orderTM);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        if (tblOrders.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please add items to cart..!").show();
            return;
        }

//        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();

        if (selectedCustomerId == null) {
            new Alert(Alert.AlertType.WARNING, "Please select customer for place order..!").show();
            return;
        }

        String orderId = lblOrderId.getText();
        Date dateOfOrder = Date.valueOf(lblOrderDate.getText());


        ArrayList<OrderItemDTO> orderItemList = new ArrayList<>();

        for (OrderTM orderTM : orderData) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(
                    orderId,
                    orderTM.getProductId(),
                    orderTM.getOrderQty(),
                    orderTM.getPrice()
            );
            orderItemList.add(orderItemDTO);
        }

        OrdersDTO ordersDTO = new OrdersDTO(
                orderId,
                selectedCustomerId,
                dateOfOrder,
                orderItemList
        );

//        cartData
//        orderDetail -> {orderId, itemId, qty, price}
        try {
            boolean isSaved = placeOrder(ordersDTO);
            System.out.println(isSaved);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Order saved").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Order fail..!").show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Order fail..!"+e.getMessage()).show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }

    }

    private void setCellValues() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        tblOrders.setItems(orderData);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(orderBO.getNextOrderId());
        lblOrderDate.setText(LocalDate.now().toString());
        loadCustomerIds();
        loadProductIds();
        loadProductSizes();
        orderData.clear();
    }

    private void loadProductIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> productIdsList = orderBO.getAllProductIds();
        ObservableList<String> productIds = FXCollections.observableArrayList();
        productIds.addAll(productIdsList);
        cmbProductId.setItems(productIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIdsList = orderBO.getAllCustomerIds();
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        customerIds.addAll(customerIdsList);
        cmbCustomerId.setItems(customerIds);
    }

    private void loadProductSizes() throws SQLException, ClassNotFoundException {
        ArrayList<String> productSizesList = orderBO.getAllProductSizes();
        ObservableList<String> productSizes = FXCollections.observableArrayList();
        productSizes.addAll(productSizesList);
        cmbSize.setItems(productSizes);
    }

    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection connection =null;
        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
////            boolean isOrderSave = CrudUtil.execute(
////                    "insert into orders values (?,?,?)",
////                    ordersDTO.getOrderId(),
////                    ordersDTO.getCustomerId(),
////                    ordersDTO.getOrderDate()
////            );
//
//            boolean isOrderSave = orderDAO.save(ordersDTO);
//            System.out.println("isOrderSave = " + isOrderSave);
//
//            if (isOrderSave) {
//                boolean isOrderDetailsSaved = orderItemDAO.saveDetailsList(ordersDTO.getOrderItems());
//                System.out.println("isOrderDetailsSaved = " + isOrderDetailsSaved);
//                System.out.println("isOrderSave = " + isOrderSave);
//                if (isOrderDetailsSaved) {
//                    connection.commit();
//                    return true;
//                }
//            }
            return orderBO.placeOrder(ordersDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
