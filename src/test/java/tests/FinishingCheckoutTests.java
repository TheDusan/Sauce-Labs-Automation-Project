package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import utils.Data;
import utils.SeleniumCommands;

public class FinishingCheckoutTests extends BaseTest {

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
        SeleniumCommands.clickOnElement(checkoutFirstPage.firstNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.firstNameInputField, Data.RANDOM_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.lastNameInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.lastNameInputField, Data.RANDOM_LAST_NAME);
        SeleniumCommands.clickOnElement(checkoutFirstPage.postalCodeInputField);
        SeleniumCommands.sendKeys(checkoutFirstPage.postalCodeInputField, Data.RANDOM_POSTAL_CODE);
        SeleniumCommands.clickOnElement(checkoutFirstPage.continueButton);
    }

    @Test
    public void checkoutDisplaysTotalPriceCorrectly() {
        double calculatedTotal = checkoutSecondPage.itemPrices.stream()
                .mapToDouble(element -> {
                    String priceText = element.getText();
                    return Double.parseDouble(priceText.replace("$", "").trim());
                })
                .sum();

        String displayedTotalText = checkoutSecondPage.totalPrice.getText();
        double displayedTotal = Double.parseDouble(displayedTotalText.replace("Item total: $", "").trim());

        Assert.assertEquals(calculatedTotal, displayedTotal, 0.01);
    }

    @Test
    public void userCanFinishTheCheckoutProcess() {
        SeleniumCommands.clickOnElement(checkoutSecondPage.finishButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.CHECKOUT_FINISH);
        Assert.assertTrue(checkoutCompletePage.backToProductsButton.isDisplayed());
        Assert.assertTrue(checkoutCompletePage.header.isDisplayed());
        Assert.assertEquals(checkoutCompletePage.header.getText(), Data.HEADER_TEXT);
    }

    @Test
    public void userCanCancelCheckoutProcessFromCheckoutStepTwoPage() {
        SeleniumCommands.clickOnElement(checkoutSecondPage.cancelButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.INVENTORY_PAGE);
    }

    @Test
    public void backHomeButtonNavigatesToHome() {
        SeleniumCommands.clickOnElement(checkoutSecondPage.finishButton);
        SeleniumCommands.clickOnElement(checkoutCompletePage.backToProductsButton);

        Assert.assertEquals(driver.getCurrentUrl(), Data.INVENTORY_PAGE);
    }

    public void tearDown() {
        driver.quit();
    }
}
