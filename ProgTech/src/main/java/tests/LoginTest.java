package tests;

import Classes.Controllers.LoginController;
import Classes.Users;
import database.MySQLConnect;
import Frontend.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class LoginTest {
    @Mock
    Login testLogin;

    LoginController testController = new LoginController(testLogin);

    @Test
    public void getAuthenticatedUserTestIncorrectData() throws SQLException {
        MySQLConnect.connectDatabase();
        Users testUser = testController.getAuthenticatedUser("AAA", "BBB");
        Assertions.assertNull(testUser.username);
    }

    @Test
    public void getAuthenticatedUserTestCorrectData() throws SQLException {
        MySQLConnect.connectDatabase();
        Users testUser = testController.getAuthenticatedUser("test", "test");
        Assertions.assertEquals("test", testUser.username);
    }


}

