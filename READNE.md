# 💊 Pharmacy Management System
 
A desktop-based Pharmacy Management System (PMS) built with **Java Swing** and **MySQL** to automate daily pharmacy operations — managing medicines, users, billing, and sales through a role-based interface.
 
> 🎓 Academic project — Riphah International University, Database System (Spring 2026)
> Supervisor: **Farwa Nawaz**
 
| Student | SAP ID | Program |
|---|---|---|
| Hafsa Saghir | 62421 | BSSE-4 |
| Mishal Asim | 62516 | BSSE-4 |
 
---
 
## 📑 Table of Contents
 
- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Database Design](#-database-design)
- [Getting Started](#-getting-started)
- [Sample SQL Queries](#-sample-sql-queries)
- [Screens](#-screens)
- [Testing](#-testing)
- [Results](#-results)
- [Roadmap](#-roadmap)
- [License](#-license)
---
 
## 📖 Overview
 
The PMS digitizes pharmacy operations to reduce manual paperwork, minimize errors, and improve efficiency. It enforces **role-based access control** with two roles — **Admin** and **Salesman** — each routed to a dedicated dashboard with restricted functionality. All data is persisted in a MySQL database named `pharmacy`, built around three core tables: `appuser`, `medicine`, and `bill`.
 
## ✨ Features
 
**Authentication**
- Secure login screen; role read from the `appuser` table routes users to the correct dashboard
**Admin**
- ➕ Register new users (Admin or Salesman) with full personal details
- 📋 View all registered users in a table (click a row to delete)
- 🔍 Search a user by username and update their details
- 👤 View and update own profile
**Salesman**
- ➕ Add new medicine records (ID, name, company, quantity, price)
- 📋 View all medicines (click a row to delete)
- 🔍 Search medicine by ID and update details, including restocking quantity
- 🛒 Select medicines, add to a cart, and generate a printed bill
- 🧾 View bill history
- 👤 View and update own profile
Both roles support logout and exit, each with a confirmation dialog.
 
## 🛠 Tech Stack
 
| Component | Technology |
|---|---|
| Operating System | Windows 10 / 11 |
| Frontend | Java Swing (JDK 17+) |
| Database | MySQL 8.x |
| DB Client | MySQL Workbench |
| Connectivity | JDBC |
| IDE | Apache NetBeans / IntelliJ IDEA |
| Hardware | Minimum 4 GB RAM |
 
## 🗄 Database Design
 
### Entity Relationship
 
```
appuser  1───M  medicine
appuser  1───M  bill
```
 
### Relational Schema
 
```
appuser  (appuser_pk, userRole, name, dob, mobileNumber, email, username, password, address)
medicine (medicine_pk, uniqueId, name, companyName, quantity, price)
bill     (bill_pk, billId, billDate, totalPaid, generatedBy)
```
 
<details>
<summary><strong>Table: appuser</strong></summary>
| Column | Type | Constraints | Description |
|---|---|---|---|
| appuser_pk | INT | PRIMARY KEY, AI | Auto-increment surrogate key |
| userRole | VARCHAR(50) | NOT NULL | Role: Admin or Salesman |
| name | VARCHAR(100) | NOT NULL | Full name of the user |
| dob | DATE | | Date of birth |
| mobileNumber | VARCHAR(20) | | Contact number |
| email | VARCHAR(100) | | Email address |
| username | VARCHAR(50) | UNIQUE | Login username |
| password | VARCHAR(50) | NOT NULL | Login password |
| address | VARCHAR(200) | | Home/work address |
 
</details>
<details>
<summary><strong>Table: medicine</strong></summary>
| Column | Type | Constraints | Description |
|---|---|---|---|
| medicine_pk | INT | PRIMARY KEY, AI | Auto-increment surrogate key |
| uniqueId | VARCHAR(20) | NOT NULL | Medicine identifier |
| name | VARCHAR(100) | NOT NULL | Medicine name |
| companyName | VARCHAR(100) | | Manufacturing company |
| quantity | BIGINT | | Available stock quantity |
| price | BIGINT | | Price per unit (Rs.) |
 
</details>
<details>
<summary><strong>Table: bill</strong></summary>
| Column | Type | Constraints | Description |
|---|---|---|---|
| bill_pk | INT | PRIMARY KEY, AI | Auto-increment surrogate key |
| billId | VARCHAR(50) | NOT NULL | Unique bill identifier |
| billDate | DATE | | Date the bill was generated |
| totalPaid | BIGINT | | Total amount paid (Rs.) |
| generatedBy | VARCHAR(50) | | Username of the salesman |
 
</details>
## 🚀 Getting Started
 
### Prerequisites
- JDK 17+
- MySQL 8.x + MySQL Workbench
- NetBeans or IntelliJ IDEA
### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/pharmacy-management-system.git
cd pharmacy-management-system
```
 
### 2. Create the database
```sql
CREATE DATABASE pharmacy;
USE pharmacy;
 
CREATE TABLE appuser (
  appuser_pk INT PRIMARY KEY AUTO_INCREMENT,
  userRole VARCHAR(50) NOT NULL,
  name VARCHAR(100) NOT NULL,
  dob DATE,
  mobileNumber VARCHAR(20),
  email VARCHAR(200),
  username VARCHAR(50) UNIQUE,
  password VARCHAR(50) NOT NULL,
  address VARCHAR(200)
);
 
CREATE TABLE medicine (
  medicine_pk INT PRIMARY KEY AUTO_INCREMENT,
  uniqueId VARCHAR(20) NOT NULL,
  name VARCHAR(100) NOT NULL,
  companyName VARCHAR(100),
  quantity BIGINT,
  price BIGINT
);
 
CREATE TABLE bill (
  bill_pk INT PRIMARY KEY AUTO_INCREMENT,
  billId VARCHAR(50) NOT NULL,
  billDate DATE,
  totalPaid BIGINT,
  generatedBy VARCHAR(50)
);
```
 
### 3. Configure the connection
Update the JDBC connection string, username, and password in the project's database config file to point to your local `pharmacy` database.
 
### 4. Run
Open the project in NetBeans / IntelliJ IDEA and run the main class. Log in with a seeded admin account to manage users, or a salesman account to manage medicines and sales.
 
## 🔎 Sample SQL Queries
 
**Login authentication**
```sql
SELECT * FROM pharmacy.appuser WHERE username='admin' AND password='admin';
```
 
**Add a medicine**
```sql
INSERT INTO pharmacy.medicine (uniqueId, name, companyName, quantity, price)
VALUES ('100', 'Panadol', 'testing', 2, 50);
```
 
**Join bills with the salesman who generated them**
```sql
SELECT b.billId, b.billDate, b.totalPaid, a.name AS salesmanName
FROM pharmacy.bill b
JOIN pharmacy.appuser a ON b.generatedBy = a.username;
```
 
**Total sales per salesman**
```sql
SELECT generatedBy, COUNT(*) AS totalBills, SUM(totalPaid) AS totalRevenue
FROM pharmacy.bill
GROUP BY generatedBy;
```
 
**Medicines priced above average**
```sql
SELECT name, price FROM pharmacy.medicine
WHERE price > (SELECT AVG(price) FROM pharmacy.medicine);
```
 
## 🖥 Screens
 
| Screen | Description |
|---|---|
| **Login** | Entry point; validates credentials against `appuser` and routes to the correct dashboard |
| **Admin Dashboard** | Add User, View User, Update User, Profile, Logout, Exit |
| **Salesman Dashboard** | Add Medicine, Sell Medicine, View Medicine, View Bill, Update Medicine, Profile, Logout, Exit |
| **Sell Medicine** | Searchable medicine list, auto-filled details, cart-based checkout with automatic total calculation and bill printing |
 
## 🧪 Testing
 
| Test ID | Test Case | Result |
|---|---|---|
| PMS_Login_01 | Valid login routes to correct dashboard | ✅ Pass |
| PMS_Login_02 | Invalid login (wrong password) is rejected | ✅ Pass |
| PMS_User_01 | Admin adds a new Salesman user | ✅ Pass |
| PMS_Med_01 | Salesman adds a new medicine record | ✅ Pass |
| PMS_Bill_01 | Salesman sells medicine and generates a bill | ✅ Pass |
 
## 📊 Results
 
- Role-based access control correctly separates Admin and Salesman functionality
- Full CRUD implemented for both users and medicines via JDBC
- Sales module computes totals dynamically and records bills with a unique Bill ID, date, total, and salesman identifier
- Primary keys and auto-increment fields maintain data integrity; `generatedBy` links bills to the issuing salesman
## 🗺 Roadmap
 
- [ ] Password hashing for stored credentials
- [ ] Dedicated `bill_items` table for per-bill line items
- [ ] Reporting features (daily/monthly sales summaries)
- [ ] Inventory alerts
- [ ] Multi-branch support
## 📄 License
 
This project was developed for academic purposes as part of the Database System course at Riphah International University. Add a license (e.g. MIT) here if you intend to open-source it.
 
---
 
<p align="center">Built with ☕ and SQL by Hafsa Saghir & Mishal Asim</p>
 