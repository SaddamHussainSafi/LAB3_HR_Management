# JavaFX HR Management System - Lab 3

This is a simple HR Management desktop application built using JavaFX, MySQL, and Maven. It supports login authentication, a dashboard, and separate panels for Admin and Employee record management.

## Project Structure

src/
├── main/
│ ├── java/com/example/
│ │ ├── App.java
│ │ ├── PrimaryController.java
│ │ ├── SecondaryController.java
│ │ ├── AdminController.java
│ │ ├── EmployeeController.java
│ │ └── SalaryTest.java
│ └── resources/com/example/
│ ├── primary.fxml
│ ├── secondary.fxml
│ ├── admin.fxml
│ └── employee.fxml

sql
Copy
Edit

## Features

- JavaFX Login system with email/password
- Dashboard with navigation buttons
- Admin panel with CRUD functionality
- Employee panel with CRUD functionality
- Connected to MySQL using JDBC
- JUnit tested salary calculation

## Login Credentials

Make sure you have an entry in your `admin` table in MySQL:

```sql
CREATE DATABASE hr_lab3;
USE hr_lab3;

CREATE TABLE admin (
  id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255),
  password VARCHAR(255)
);

INSERT INTO admin (email, password) VALUES ('saddam@hr.com', '456');
Technologies Used
JavaFX 17

Java 17

MySQL (XAMPP)

Maven

JUnit 5

VS Code

JUnit Testing (Salary Calculation)
JUnit is used to test the salary logic:

java
Copy
Edit
@Test
public void testYearlySalaryForManager() {
    Salary salary = new Salary(5000);
    double expected = 5000 * 12;
    assertEquals(expected, salary.calculateYearlySalary());
}
✅ Tests include:

Basic salary

Zero salary

High salary

Screenshots
Add screenshots below once available:

✅ Login Screen

✅ Dashboard Page

✅ Admin Panel View

✅ Employee Panel View

✅ MySQL Tables

✅ JUnit Test Results

How to Run
bash
Copy
Edit
# Clone the repo
git clone https://github.com/SaddamHussainSafi/LAB3_HR_Management

# Navigate to project
cd LAB3_HR_Management

# Run with Maven
mvn clean javafx:run
Make sure XAMPP is running and MySQL is active.

Disclaimer
This project was developed as part of Lab 3 at Sault College by Saddam Hussain Safi. ChatGPT was used to assist with logic verification and debugging. All implementation, testing, and documentation was done independently.