
package oop_project.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oop_project.View.user_admin_login;
import oop_project.DAO.userDAO;
import oop_project.View.admin_choose_page;
import oop_project.Model.user_loginModel;
import oop_project.View.user_choose_page;
import javax.swing.JOptionPane;

public class user_login_controller {
    private final userDAO userdao;
    private final user_admin_login login;

    public user_login_controller(userDAO userdao, user_admin_login login) {
        this.userdao = userdao;
        this.login = login;

        this.login.addLoginButtonListener(new loginButtonListener());
    }

    class loginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = login.getusername_field().getText().trim();
            String password = new String(login.getpassword_field().getPassword()).trim();

            // Validate user input
            if (username.isEmpty() || password.isEmpty()) {
                login.setMessage("Username and Password cannot be empty.");
                return;
            }

            String adminUsername = "admin";
            String adminPassword = "automobileadmin";

            // Check if the user is admin
            if (adminUsername.equals(username) && adminPassword.equals(password)) {
                admin_choose_page choose1 = new admin_choose_page();
                choose1.setVisible(true);
                login.dispose();
            } else {
                user_loginModel loginmodel = new user_loginModel(username, password);
                boolean isRegularUser = false;
                try {
                    isRegularUser = userdao.getdata(loginmodel);
                } catch (SQLException ex) {
                    Logger.getLogger(user_login_controller.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(login, "An error occurred while accessing the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (isRegularUser) {
                    user_choose_page choose = new user_choose_page();
                    choose.setVisible(true);
                    login.dispose();
                } else {
                    login.setMessage("Login failed.");
                }
            }
        }
    }
}
