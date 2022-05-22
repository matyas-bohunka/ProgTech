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
                    + programUsername + "', sha('" + programPassword + "'));";
            MySQLConnect.modifyDatabase(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getTableSize() {
        form.logger.info("Requesting table size from database.");
        int size = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM spaceships where uid ='"
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
            String query = "SELECT spaceships.type , spaceships.fuel, spaceships.consumption, spaceships.price,\n" +
                    "weapons.name AS 'Weapon', \n" +
                    "power_plants.name AS 'Power Plant',\n" +
                    "quantum_drives.name AS 'Quantum Drive' \n" +
                    "FROM((spaceships INNER JOIN weapons ON spaceships.weapon = weapons.id)\n" +
                    "INNER JOIN power_plants ON spaceships.power_plant = power_plants.id)\n" +
                    "INNER JOIN quantum_drives ON spaceships.quantum_drive = quantum_drives.id\n" +
                    "WHERE spaceships.uid ='" + MySQLConnect.connectedUSer.id + "';";

            ResultSet res = MySQLConnect.executeQuery(query);
            String columns[] = {"Type", "Fuel", "Consumption", "Price",
                    "Weapon", "Power Plant", "Quantum drive"};
            String data[][] = new String[size][7];
            int i = 0;
            while (res.next()) {
                String type = res.getString("type");
                String fuel = res.getString("fuel");
                int consumption = res.getInt("consumption");
                int price = res.getInt("price");
                String weapon = res.getString("Weapon");
                String powerPlant = res.getString("Power Plant");
                String quantumDrive = res.getString("Quantum Drive");

                data[i][0] = type;
                data[i][1] = fuel;
                data[i][2] = consumption + "";
                data[i][3] = price + "";
                data[i][4] = weapon;
                data[i][5] = powerPlant;
                data[i][6] = quantumDrive;

                i++;
            }
            createShipTable(data, columns);
            form.logger.info("Preparation successful.");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void createShipTable(String data[][], String columns[]) {
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

    public void createNewShipButtonClicked() {
        form.logger.info("Create ship button pressed.");
        form.dispose();
        Config conf = new Config();
    }

    public void checkOrdersButtonClicked() {
        form.logger.info("Check orders button pressed");
        prepareDataForTable(getTableSize());
        form.logger.info("Table successfully created.");
    }
}
