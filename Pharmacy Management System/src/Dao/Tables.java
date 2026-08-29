/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author SR Laptop
 */
public class Tables {
    public static void main(String[] args)
    {
        try{
            Connection con= ConnectionProvider.getCon();
            Statement st= con.createStatement();
            //st.executeUpdate("create table if not exists appuser(appuser_pk int primary key auto_increment,userRole varchar(200),name varchar(200),dob varchar(50),mobileNumber varchar(50),email varchar (200),username varchar(200),password varchar(50),address varchar(200))");
            //st.executeUpdate("insert into appuser(userRole,name,dob,mobileNumber,email,username,password,address) values('Admin','Admin','16-12-2005','03127147907','admin05@email.com','admin','admin','Pakistan')");
            //st.executeUpdate("create table medicine(medicine_pk int primary key auto_increment,uniqueId varchar(200),name varchar(200),companyName varchar(200),quantity bigint,price bigint)");
            //st.executeUpdate("CREATE TABLE IF NOT EXISTS bill ( bill_pk INT PRIMARY KEY AUTO_INCREMENT, billId VARCHAR(200), billDate VARCHAR(200), totalPaid BIGINT, generatedBy VARCHAR(50) )");

            JOptionPane.showMessageDialog(null, "Table created successfully");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
}
