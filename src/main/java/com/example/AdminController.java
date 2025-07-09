package com.example;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class AdminController {

    @FXML private TableView<Admin> adminTable;
    @FXML private TableColumn<Admin, Integer> colId;
    @FXML private TableColumn<Admin, String> colName;
    @FXML private TableColumn<Admin, String> colEmail;

    @FXML private TextField nameField, emailField;
    @FXML private PasswordField passwordField;

    private final ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());

        loadAdminData();
    }

    public void loadAdminData() {
        adminList.clear();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM admin");
            while (rs.next()) {
                adminList.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
            adminTable.setItems(adminList);
        } catch (Exception e) {
            showAlert("Failed to load data: " + e.getMessage());
        }
    }

    public void handleCreate() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO admin (name, email, password) VALUES (?, ?, ?)");
            stmt.setString(1, nameField.getText());
            stmt.setString(2, emailField.getText());
            stmt.setString(3, passwordField.getText());
            stmt.executeUpdate();
            clearFields();
            loadAdminData();
        } catch (Exception e) {
            showAlert("Create failed: " + e.getMessage());
        }
    }

    public void handleUpdate() {
        Admin selected = adminTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE admin SET name=?, email=?, password=? WHERE id=?");
            stmt.setString(1, nameField.getText());
            stmt.setString(2, emailField.getText());
            stmt.setString(3, passwordField.getText());
            stmt.setInt(4, selected.getId());
            stmt.executeUpdate();
            clearFields();
            loadAdminData();
        } catch (Exception e) {
            showAlert("Update failed: " + e.getMessage());
        }
    }

    public void handleDelete() {
        Admin selected = adminTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM admin WHERE id=?");
            stmt.setInt(1, selected.getId());
            stmt.executeUpdate();
            loadAdminData();
        } catch (Exception e) {
            showAlert("Delete failed: " + e.getMessage());
        }
    }

    public void goBack() {
        try {
            App.setRoot("secondary");
        } catch (Exception e) {
            showAlert("Back navigation error: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
