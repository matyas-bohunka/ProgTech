package Classes.Controllers;

import Classes.Users;
import database.MySQLConnect;
import Frontend.Login;
import Frontend.Config;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoginController {

    private Login form;

    public LoginController(Login f) {
        form = f;
    }

    public boolean isUsernamePasswordEmpty(String usernameText, String passwordText) {
        if (!usernameText.isEmpty() && !passwordText.isEmpty()) {
            String username = form.usernameField.getText();
            String password = form.passwordField.getText();
            loginButtonClicked(username, password);
            return true;

        } else {
            JOptionPane.showMessageDialog(form,
                    "Please type in a username and password", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void loginButtonClicked(String usr, String pswd) {
        form.logger.info("Login button clicked.");

        user = getAuthenticatedUser(usr, pswd);
        if (user.username != null && user.password != null) {
            MySQLConnect.connectedUSer = user;
            form.logger.info("User logged in.");
            form.loginPanel.setVisible(false);
            form.choicePanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(form,
                    "Wrong username or password.", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Users user;

    public Users getAuthenticatedUser(String programUsername, String programPassword) {
        Users user = new Users();

        try {
            String sql = "SELECT * FROM users WHERE username='"
                    + programUsername + "' AND password=sha('" + programPassword + "');";
            ResultSet resultSet = MySQLConnect.executeQuery(sql);
            if (resultSet.next()) {
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
                user.id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            form.logger.warn(e.getMessage());
        }
        return user;
    }

    private void registerUser(String programUsername, String programPassword) {
        try {
            String sql = "INSERT INTO users (username, password) VALUES('"
                    + programUsername + "', md5('" + programPassword + "'));";
            MySQLConnect.modifyDatabase(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getTableSize() {
        form.logger.info("Requesting table size from database.");
        int size = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM laptops where uid ='"
                    + MySQLConnect.connectedUSer.id + "';";
            ResultSet res = MySQLConnect.executeQuery(query);
            res.next();
            size = res.getInt(1);

            form.logger.info("Request successful.");
        } catch (Exception x) {

            x.printStackTrace();
        }
        return size;
    }

    private void prepareDataForTable(int size) {
        try {
            form.logger.info("Preparing data for table.");
            String query = "SELECT laptops.type , laptops.price,\n" +
                    "grapgics_card.name AS 'Graphics Card', \n" +
                    "storage.name AS 'Storage',\n" +
                    "processor.name AS 'Processor' \n" +
                    "memory.name AS 'Memmory' \n" +
                    "os.name AS 'Os' \n" +
                    "FROM((laptops INNER JOIN graphics_card ON laptops.graphics_card = graphics_card.id)\n" +
                    "INNER JOIN storage ON laptops.storage = storage.id)\n" +
                    "INNER JOIN processor ON laptops.processor = processor.id)\n" +
                    "INNER JOIN memory ON laptops.memory = storage.memory)\n" +
                    "INNER JOIN os ON laptops.os = os.id\n" +
                    "WHERE laptops.uid ='" + MySQLConnect.connectedUSer.id + "';";

            ResultSet res = MySQLConnect.executeQuery(query);
            String columns[] = {"Type", "Price",
                    "Graphics Card", "Storage", "Processor", "Memory", "Os"};
            String data[][] = new String[size][7];
            int i = 0;
            while (res.next()) {
                String type = res.getString("type");
                int price = res.getInt("price");
                String graphics_card = res.getString("Graphics card");
                String storage = res.getString("Storage");
                String processor = res.getString("Processor");
                String memory = res.getString("Memory");
                String os = res.getString("Os");

                data[i][0] = type;
                data[i][1] = price + "";
                data[i][2] = graphics_card;
                data[i][3] = storage;
                data[i][4] = processor;
                data[i][5] = memory;
                data[i][6] = os;

                i++;
            }
            createLaptopTable(data, columns);
            form.logger.info("Preparation successful.");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void createLaptopTable(String data[][], String columns[]) {
        form.logger.info("Creating table.");
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame("Populate JTable from Database");
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(500, 250);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f.setVisible(true);
        form.setVisible(false);
        Login temp = form;
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                f.dispose();
                temp.setVisible(true);
            }
        });
    }

    public void registrationButtonClicked() {
        form.logger.info("Registration button clicked.");
        form.loginPanel.setVisible(false);
        form.registrationPanel.setVisible(true);
    }

    public void regButtonClicked() {
        form.logger.info("Registration button clicked on registration panel.");
        if (!form.regUsernameField.getText().isEmpty() && !form.regUsernameField.getText().isEmpty()) {
            String username = form.regUsernameField.getText();
            String password = form.regPasswordField.getText();
            registerUser(username, password);
            form.logger.info("User created and stored in database successfully.");
            form.registrationPanel.setVisible(false);
            form.loginPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(form,
                    "Please type in a username and password", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cancelButtonClicked() {
        form.logger.info("Registration cancel button pressed");
        form.registrationPanel.setVisible(false);
        form.loginPanel.setVisible(true);
    }

    public void createLaptopButtonClicked() {
        form.logger.info("Order new Laptop button pressed.");
        form.dispose();
        Config conf = new Config();
    }

    public void checkOrdersButtonClicked() {
        form.logger.info("Check orders button pressed");
        prepareDataForTable(getTableSize());
        form.logger.info("Table successfully created.");
    }
}
