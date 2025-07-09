package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.LocalDate;

public class SecondaryController {

    @FXML private Label welcomeLabel;

    @FXML
    public void initialize() {
        String user = "Saddam"; // This could later be passed dynamically
        String today = LocalDate.now().toString();
        welcomeLabel.setText("Welcome, " + user + " | Today: " + today);
    }

    @FXML
    private void openAdmin() {
        try {
            App.setRoot("admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEmployee() {
        try {
            App.setRoot("employee");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            App.setRoot("primary");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }
}
