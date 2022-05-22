package Frontend;
import Classes.Controllers.LoginController;
import Database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Classes.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog {
    public JPanel loginPanel;
    public JPanel registrationPanel;
    public JPanel choicePanel;
    private JPanel overall;
    public JTextField usernameField;
    public JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;
    public JTextField regUsernameField;
    public JPasswordField regPasswordField;
    private JButton regButton;
    private JButton cancelButton;
    private JButton orderNewLaptopButton;
    private JButton checkOrdersButton;
    public static Logger logger = LoggerFactory.getLogger(LoginFrame.class);
    private LoginController controller;

    public Login(boolean first) {
        setTitle("Login");
        setContentPane(overall);
        setSize(800, 600);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if (first) {
            registrationPanel.setVisible(false);
            choicePanel.setVisible(false);
        } else {
            loginPanel.setVisible(false);
            registrationPanel.setVisible(false);
        }

        controller = new LoginController(this);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.isUsernamePasswordEmpty(usernameField.getText(), passwordField.getText());
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrationButtonClicked();
            }
        });
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.regButtonClicked();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cancelButtonClicked();
            }
        });
        orderNewLaptopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createNewLaptopButtonClicked();
            }
        });
        checkOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkOrdersButtonClicked();
            }
        });
        setVisible(true);
    }
}
