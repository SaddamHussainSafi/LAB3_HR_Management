package com.example;

import javafx.beans.property.*;

public class Employee {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty department;
    private final StringProperty role;
    private final DoubleProperty salary;

    public Employee(int id, String name, String department, String role, double salary) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.role = new SimpleStringProperty(role);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getDepartment() { return department.get(); }
    public String getRole() { return role.get(); }
    public double getSalary() { return salary.get(); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty departmentProperty() { return department; }
    public StringProperty roleProperty() { return role; }
    public DoubleProperty salaryProperty() { return salary; }
}
