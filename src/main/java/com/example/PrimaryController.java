package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class PrimaryController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hr_lab3", "root", "")) {
            String sql = "SELECT * FROM admin WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                App.setRoot("secondary");
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        } catch (Exception e) {
            errorLabel.setText("DB Error: " + e.getMessage());
        }
    }
}
