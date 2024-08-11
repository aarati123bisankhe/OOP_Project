/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_project.Controller;



import oop_project.Model.user_signupModel;
import oop_project.DAO.userDAO;
import oop_project.View.user_signup;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class user_signup_controller {
    private final user_signup signin;
    private final userDAO userdao;
 
    public user_signup_controller(user_signup signinin, userDAO userdao) {
        this.signin = signinin;
        this.userdao = userdao;
 
        
        this.signin.addRegisterButtonListener(new RegisterButtonListener());
    }
 
    class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = signin.getUsername_field().getText();
            String address = signin.getAddress_field().getText();
            String set_password = signin.getSet_field().getText();
            String contact = signin.getContact_field().getText();
            String confirm_password = signin.getConfirm_field().getText();
            String email = signin.getEmail_field().getText();
            String citizenship = signin.getCitizenship_field().getText();
            String license = signin.getLicense_field().getText();
            
            
            if (!set_password.equals(confirm_password)) {
                signin.setMessage("Passwords do not match!");
                return;
            }
 
            user_signupModel signinmodel = new user_signupModel( username, address, set_password, contact, confirm_password,email, citizenship,license);
            boolean isRegistered = true;
            try{
                isRegistered = userdao.insertSignin(signinmodel);
            }catch(SQLException ex){
                System.out.println(ex);
            }
            if (isRegistered){
                signin.setMessage("Registration successful!");
            } else {
                signin.setMessage("Registration failed. Please try again.");
            }
        }
    }
}
 