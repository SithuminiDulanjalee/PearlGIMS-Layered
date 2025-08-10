package lk.ijse.pearlgims.controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
//import lk.ijse.pearlgims.model.CustomerModel;
//import lk.ijse.pearlgims.model.OrdersModel;
//import lk.ijse.pearlgims.model.SupplierModel;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public BarChart<String, Number> barChart;

    public Label lblCustomer;
    public Label lblOrders;
    public Label lblSuppliers;
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Orders");
        try {
//            Map<String, Integer> orderData = new OrdersModel().getOrderCountByDate();
//
//            for (Map.Entry<String, Integer> entry : orderData.entrySet()) {
//                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
//            }
//            int count = new CustomerModel().getCount();
//            lblCustomer.setText(String.valueOf(count));
//
//            int ordersCount = new OrdersModel().getOrderCount();
//            lblOrders.setText(String.valueOf(ordersCount));
//
//            int suppliersCount = new SupplierModel().getSupplierCount();
//            lblSuppliers.setText(String.valueOf(suppliersCount));

        } catch (Exception e) {
            e.printStackTrace();
        }
        barChart.getData().clear();
        barChart.getData().add(series);

    }
}