package com.video.rental.app.controllers.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainController {

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        showDashboard();
    }

    private void showDashboard() {
        try {
            Stage dasboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/DashboardView.fxml"));
            FlowPane dashboardLayout = new FlowPane();
            dashboardLayout = loader.load();
            Scene scene = new Scene(dashboardLayout);
            dasboardStage.setScene(scene);
            dasboardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
