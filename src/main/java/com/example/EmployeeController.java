package com.example;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class EmployeeController {

    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> colId;
    @FXML private TableColumn<Employee, String> colName, colDepartment, colRole;
    @FXML private TableColumn<Employee, Double> colSalary;

    @FXML private TextField nameField, departmentField, roleField, salaryField;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colDepartment.setCellValueFactory(data -> data.getValue().departmentProperty());
        colRole.setCellValueFactory(data -> data.getValue().roleProperty());
        colSalary.setCellValueFactory(data -> data.getValue().salaryProperty().asObject());

        loadEmployeeData();
    }

    public void loadEmployeeData() {
        employeeList.clear();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM employee_detail");
            while (rs.next()) {
                employeeList.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                ));
            }
            employeeTable.setItems(employeeList);
        } catch (Exception e) {
            showAlert("Error loading data: " + e.getMessage());
        }
    }

    public void handleCreate() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO employee_detail (name, department, role, salary) VALUES (?, ?, ?, ?)");
            stmt.setString(1, nameField.getText());
            stmt.setString(2, departmentField.getText());
            stmt.setString(3, roleField.getText());
            stmt.setDouble(4, Double.parseDouble(salaryField.getText()));
            stmt.executeUpdate();
            clearFields();
            loadEmployeeData();
        } catch (Exception e) {
            showAlert("Create failed: " + e.getMessage());
        }
    }

    public void handleUpdate() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE employee_detail SET name=?, department=?, role=?, salary=? WHERE id=?");
            stmt.setString(1, nameField.getText());
            stmt.setString(2, departmentField.getText());
            stmt.setString(3, roleField.getText());
            stmt.setDouble(4, Double.parseDouble(salaryField.getText()));
            stmt.setInt(5, selected.getId());
            stmt.executeUpdate();
            clearFields();
            loadEmployeeData();
        } catch (Exception e) {
            showAlert("Update failed: " + e.getMessage());
        }
    }

    public void handleDelete() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hr_lab3", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM employee_detail WHERE id=?");
            stmt.setInt(1, selected.getId());
            stmt.executeUpdate();
            loadEmployeeData();
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
        departmentField.clear();
        roleField.clear();
        salaryField.clear();
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
