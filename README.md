# Employee Payroll Project (Java + MySQL)

A simple Java GUI application to manage employee payroll data using JDBC and MySQL.

## 💡 Features
- Add new employees (ID, Name, Salary)
- View all employees
- Update employee details
- Delete employee
- GUI built with Java Swing
- Uses JDBC (MySQL) without Maven

## 🛠️ Requirements
- Java 17+
- MySQL Server running locally
- MySQL JDBC Driver (place inside `lib/` folder)

## 🧠 Database Setup

Run this SQL to create the database and table:

```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100)
    -- add salary column if needed
);
ALTER TABLE employees ADD COLUMN salary DOUBLE;

📷 Screenshots

Image1- https://github.com/user-attachments/assets/08d0b776-2ddc-4cb3-b6f5-bdb36ab53e9d
Image2- https://github.com/user-attachments/assets/9bf24485-a2d0-4fb6-9256-15ee779f91fe
Image3- https://github.com/user-attachments/assets/f6ec9808-36fa-446c-ba99-0655fc279e26
Image4- https://github.com/user-attachments/assets/e08382dc-13b8-4ca2-84bb-f671666bf3ce
Image5- https://github.com/user-attachments/assets/1ba0387c-4378-4faa-ad93-85301fefc3d3
Image6- https://github.com/user-attachments/assets/ce11b79e-cc68-4128-879b-1c70c0808e4b





