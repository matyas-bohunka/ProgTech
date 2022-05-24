import Frontend.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import database.MySQLConnect;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("project started");
        MySQLConnect.connectDatabase();
        Login login = new Login(true);
        logger.info("window initialized");

    }
}