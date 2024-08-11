/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_project.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BillPage extends JFrame {

    private JTable table;

    BillPage() {
        setTitle("Bill Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {
            "Vehicle Type", "Pick-Up Date", "Vehicle Model", 
            "Drop-Off Date", "Location", "Contact Number", 
            "Rental Duration", "Total Amount"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        fetchBillData(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        
    }

    private void fetchBillData(DefaultTableModel model) {
        String url = "jdbc:mysql://localhost:3306/project";
        String user = "root";
        String password = "root";

        String sql = "SELECT vehicle_type, pick_up_date, vehicle_model, drop_off_date, location, contact_number, rental_duration, total_amount FROM user_details";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String vehicleType = rs.getString("vehicle_type");
                String pickUpDate = rs.getString("pick_up_date");
                String vehicleModel = rs.getString("vehicle_model");
                String dropOffDate = rs.getString("drop_off_date");
                String location = rs.getString("location");
                String contactNumber = rs.getString("contact_number");
                int rentalDuration = rs.getInt("rental_duration");
                double totalAmount = rs.getDouble("total_amount");

                model.addRow(new Object[]{
                    vehicleType, pickUpDate, vehicleModel, 
                    dropOffDate, location, contactNumber, 
                    rentalDuration, totalAmount
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching bill data.");
        }
    }

    public static void main(String[] args) {
        BillPage billPage = new BillPage();
        billPage.setVisible(true);
    }
}
    
    

