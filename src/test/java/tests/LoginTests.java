package tests;

import base.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;
import utils.SeleniumCommands;

public class LoginTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to(Data.URL);
    }

    @Test
    public void userCanLogInWithValidCredentials() throws InterruptedException {
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(),Data.URL_AFTER_LOGIN);
    }

    @Test
    public void userCannotLogInWithInvalidUsername() {
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.RANDOM_NAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
        Assert.assertTrue(homepagePage.errorField.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.INVALID_CREDENTIALS_MESSAGE);
    }

    @Test
    public void userCannotLogInWithInvalidPassword() {
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.RANDOM_PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.errorField.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.INVALID_CREDENTIALS_MESSAGE);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLogInLeavingBothUsernameAndPasswordFieldsEmpty() {
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertTrue(homepagePage.errorField.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.MISSING_USERNAME_MESSAGE);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLogInLeavingPasswordFieldEmpty() {
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.errorField.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.MISSING_PASSWORD_MESSAGE);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void passwordIsMasked() {
        Assert.assertEquals(homepagePage.passwordInputField.getAttribute("type"), "password");
    }

    @Test
    public void usernameFieldIsPasswordSensitive() {
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.RANDOM_NAME.toUpperCase());
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
        Assert.assertTrue(homepagePage.errorField.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.INVALID_CREDENTIALS_MESSAGE);
    }

    @Test
    public void passwordFieldIsCaseSensitive() {
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD.toUpperCase());
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.errorField.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.INVALID_CREDENTIALS_MESSAGE);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void loginPageIsAccessibleUsingKeyboard() {
        Actions actions = new Actions(driver);
        actions.sendKeys("\t").perform();
        actions.sendKeys(Data.USERNAME).perform();
        actions.sendKeys("\t").perform();
        actions.sendKeys(Data.PASSWORD).perform();

        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(driver.getCurrentUrl(),Data.URL_AFTER_LOGIN);
    }

    @Test
    public void cartIsUnaccessibleWithoutLoggingIn() {
        driver.navigate().to(Data.CART_URL);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.CART_UNAVAILABLE);
    }

    @Test
    public void inventoryIsUnaccessibleWithoutLoggingIn() {
        driver.navigate().to(Data.INVENTORY_PAGE);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
        Assert.assertEquals(homepagePage.errorField.getText(), Data.INVENTORY_UNAVAILABLE);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
