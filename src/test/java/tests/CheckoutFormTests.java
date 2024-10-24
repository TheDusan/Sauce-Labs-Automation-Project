package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;
import utils.SeleniumCommands;

public class CheckoutFormTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to(Data.URL);
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(0));
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(1));
        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        SeleniumCommands.clickOnElement(cartPage.checkoutButton);
    }

    @Test
    public void userCanCompleteTheCheckoutProcess() {
        SeleniumCommands.clickOnElement(checkoutFirstPage.firstNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.firstNameInputField, Data.RANDOM_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.lastNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.lastNameInputField, Data.RANDOM_LAST_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.postalCodeInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.postalCodeInputField, Data.RANDOM_POSTAL_CODE);
        SeleniumCommands.clickOnElement(checkoutFirstPage.continueButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CHECKOUT_STEP_TWO);
    }

    @Test
    public void userCannotCompleteTheCheckoutProcessWithoutEneteringTheFirstName() {
        SeleniumCommands.clickOnElement(checkoutFirstPage.lastNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.lastNameInputField, Data.RANDOM_LAST_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.postalCodeInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.postalCodeInputField, Data.RANDOM_POSTAL_CODE);
        SeleniumCommands.clickOnElement(checkoutFirstPage.continueButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CHECKOUT_STEP_ONE);
        Assert.assertTrue(checkoutFirstPage.errorField.isDisplayed());
        Assert.assertEquals(checkoutFirstPage.errorField.getText(), Data.MISSING_FIRST_NAME_ERROR_MESSAGE);
    }

    @Test
    public void userCannotCompleteTheCheckoutProcessWithoutEneteringTheLastName() {
        SeleniumCommands.clickOnElement(checkoutFirstPage.firstNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.firstNameInputField, Data.RANDOM_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.postalCodeInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.postalCodeInputField, Data.RANDOM_POSTAL_CODE);
        SeleniumCommands.clickOnElement(checkoutFirstPage.continueButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CHECKOUT_STEP_ONE);
        Assert.assertTrue(checkoutFirstPage.errorField.isDisplayed());
        Assert.assertEquals(checkoutFirstPage.errorField.getText(), Data.MISSING_LAST_NAME_ERROR_MESSAGE);
    }

    @Test
    public void userCannotCompleteTheCheckoutProcessWithoutEneteringThePostalCode() {
        SeleniumCommands.clickOnElement(checkoutFirstPage.firstNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.firstNameInputField, Data.RANDOM_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.lastNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.lastNameInputField, Data.RANDOM_LAST_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.continueButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CHECKOUT_STEP_ONE);
        Assert.assertTrue(checkoutFirstPage.errorField.isDisplayed());
        Assert.assertEquals(checkoutFirstPage.errorField.getText(), Data.MISSING_POSTAL_CODE_ERROR_MESSAGE);
    }

    @Test
    public void userCanCancelTheCheckoutProcess() {
        SeleniumCommands.clickOnElement(checkoutFirstPage.cancelButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CART_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
