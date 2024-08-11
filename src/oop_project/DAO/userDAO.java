/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import oop_project.Model.user_signupModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.MySqlConnection;
import oop_project.Model.user_loginModel;
        
        
public class userDAO {
    private static final Logger LOGGER = Logger.getLogger(userDAO.class.getName());
    MySqlConnection mysql = new MySqlConnection();
    Connection conn = mysql.openConnection();
    public boolean insertSignin(user_signupModel signinModel) throws SQLException {
        String query = "INSERT INTO signin (username, address, set_password, contact_number , confirm_password,citizenship_num,license_num,email ) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, signinModel.getUsername_field());
            pstmt.setString(2, signinModel.getAddress_field());
            pstmt.setString(3, signinModel.getSet_field());
            pstmt.setString(4, signinModel.getContact_field());
            pstmt.setString(5, signinModel.getConfirm_field());
            pstmt.setString(6, signinModel.getEmail_field());
            pstmt.setString(7, signinModel.getCitizenship_field());
            pstmt.setString(8, signinModel.getLicense_field());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while inserting signin data: ", e);
            return false;
        }
        
    
    }
    public boolean getdata(user_loginModel loginmodel) throws SQLException {
    boolean status = false;
    String query = "SELECT * FROM signin WHERE username  = ? AND set_password = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, loginmodel.getusername_field());
        pst.setString(2, loginmodel.getpassword_field());
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            status = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}
    
}
