package lk.ijse.pearlgims.controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.pearlgims.bo.BOFactory;
import lk.ijse.pearlgims.bo.custom.HomePageBO;
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

    HomePageBO homePageBO = (HomePageBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HOME_PAGE);
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Orders");
        try {
            Map<String, Integer> orderData = homePageBO.getOrderCountByDate();

            for (Map.Entry<String, Integer> entry : orderData.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            int count = homePageBO.getCustomerCount();
            lblCustomer.setText(String.valueOf(count));

            int ordersCount = homePageBO.getOrderCount();
            lblOrders.setText(String.valueOf(ordersCount));

            int suppliersCount = homePageBO.getSupplierCount();
            lblSuppliers.setText(String.valueOf(suppliersCount));

        } catch (Exception e) {
            e.printStackTrace();
        }
        barChart.getData().clear();
        barChart.getData().add(series);

    }
}