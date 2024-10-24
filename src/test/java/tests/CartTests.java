package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;
import utils.SeleniumCommands;

import java.time.Duration;

public class CartTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to(Data.URL);
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(0));
    }

    @Test
    public void userCanRemoveItemFromCart() {
        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        SeleniumCommands.clickOnElement(cartPage.removeButtons.get(0));

        Assert.assertTrue(cartPage.removeButtons.isEmpty());
    }

    @Test
    public void cartRetainsItemsAfterNavigatingBack() {
        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        SeleniumCommands.clickOnElement(cartPage.continueShoppingButton);
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");

        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        Assert.assertTrue(cartPage.removeButtons.size() == 1);
    }

    @Test
    public void cartDisplaysCorrectPrice() {
        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        SeleniumCommands.clickOnElement(cartPage.continueShoppingButton);
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(1));
        String backpackPrice = inventoryPage.itemPrices.get(0).getText();
        String bikeLightPrice = inventoryPage.itemPrices.get(2).getText();
        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        Assert.assertEquals(cartPage.itemPrices.get(0).getText(), backpackPrice);
        Assert.assertEquals(cartPage.itemPrices.get(1).getText(), bikeLightPrice);
    }

    @Test
    public void cartMaintainsStateAfterPageRefresh() {
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(1));
        driver.navigate().refresh();
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "2");

        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        Assert.assertEquals(cartPage.itemNames.size(), 2);

    }

    @Test
    public void cartMaintainsStateAfterLoggingOutAndLoggingBackIn() {
        SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(hamburgerMenuPage.logutLink));
        SeleniumCommands.clickOnElement(hamburgerMenuPage.logutLink);

        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);

        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");

        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        Assert.assertEquals(cartPage.itemNames.size(), 1);
    }

    @Test
    public void checkoutButtonNavigatesToCheckOut() {
        SeleniumCommands.clickOnElement(inventoryPage.shoppingCartIcon);
        SeleniumCommands.clickOnElement(cartPage.checkoutButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CHECKOUT_STEP_ONE);
    }

}
