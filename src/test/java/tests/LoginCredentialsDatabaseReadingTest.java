package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;
import utils.Queries;
import utils.SeleniumCommands;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginCredentialsDatabaseReadingTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        String url = "jdbc:mysql://localhost:3306/sauce_data";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
        }
        driver.navigate().to(Data.URL);
    }

    @Test
    public void userLoginTests() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Queries.SELECT_QUERY)) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
                SeleniumCommands.sendKeys(homepagePage.usernameInputField, username);
                SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
                SeleniumCommands.sendKeys(homepagePage.passwordInputField, password);
                SeleniumCommands.clickOnElement(homepagePage.loginButton);
                if (username.equals(Data.LOCKED_OUT_USER)) {
                    Assert.assertTrue(homepagePage.loginButton.isDisplayed());
                    Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
                    driver.navigate().refresh();
                    continue;
                } else {
                    Assert.assertEquals(driver.getCurrentUrl(), Data.INVENTORY_PAGE);
                }
                SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
                Thread.sleep(1000);
                SeleniumCommands.clickOnElement(hamburgerMenuPage.logutLink);

                Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @AfterMethod
    public void close() throws SQLException {
        connection.close();
    }
}
