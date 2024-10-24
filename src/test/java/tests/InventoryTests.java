package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;
import utils.SeleniumCommands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to(Data.URL);
        SeleniumCommands.clickOnElement(homepagePage.usernameInputField);
        SeleniumCommands.sendKeys(homepagePage.usernameInputField, Data.USERNAME);
        SeleniumCommands.clickOnElement(homepagePage.passwordInputField);
        SeleniumCommands.sendKeys(homepagePage.passwordInputField, Data.PASSWORD);
        SeleniumCommands.clickOnElement(homepagePage.loginButton);
    }

    @Test
    public void userCanAddItemToCart() {;
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(0));

        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");
    }

    // isprazni korpu

    @Test
    public void userCanAddMultipleItemsToTheCart() throws InterruptedException {
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(0));
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(1));
        Thread.sleep(2000);

        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "2");
    }

    @Test
    public void productTitleRedirectsToProductDetailsPage() {
        SeleniumCommands.clickOnElement(inventoryPage.itemTitles.get(0));

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL_FOR_PRODUCT_DETAILS);
    }

    @Test
    public void productImageRedirectsToProductDetailsPage() {
        SeleniumCommands.clickOnElement(inventoryPage.itemImages.get(0));

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL_FOR_PRODUCT_DETAILS);
    }

    @Test
    public void verifySortingByNameAtoZ()  {
        SeleniumCommands.clickOnElement(inventoryPage.sortDropdown);
        SeleniumCommands.clickOnElement(inventoryPage.options().get(0));
        List<String> displayedTitles = inventoryPage.itemTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedTitles = new ArrayList<>(displayedTitles);
        Collections.sort(sortedTitles);

        Assert.assertEquals(inventoryPage.itemTitles.get(0).getText(), sortedTitles.get(0));
        Assert.assertEquals(displayedTitles.get(displayedTitles.size() - 1), sortedTitles.get(sortedTitles.size() - 1));;
    }

    @Test
    public void verifySortingByNameZtoA()  {
        SeleniumCommands.clickOnElement(inventoryPage.sortDropdown);
        SeleniumCommands.clickOnElement(inventoryPage.options().get(1));

        List<String> displayedTitles = inventoryPage.itemTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedTitles = new ArrayList<>(displayedTitles);
        sortedTitles.sort(Collections.reverseOrder());

        Assert.assertEquals(displayedTitles.get(0), sortedTitles.get(0));
        Assert.assertEquals(displayedTitles.get(displayedTitles.size() - 1), sortedTitles.get(sortedTitles.size() - 1));
    }

    @Test
    public void verifySortingByPriceLowToHigh() {
        SeleniumCommands.clickOnElement(inventoryPage.sortDropdown);
        SeleniumCommands.clickOnElement(inventoryPage.options().get(2));

        List<Double> displayedPrices = inventoryPage.itemPrices.stream()
                .map(price -> Double.parseDouble(price.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = new ArrayList<>(displayedPrices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(displayedPrices.get(0), sortedPrices.get(0));
        Assert.assertEquals(displayedPrices.get(displayedPrices.size() - 1), sortedPrices.get(sortedPrices.size() - 1));
    }

    @Test
    public void verifySortingByPriceHighToLow() {
        SeleniumCommands.clickOnElement(inventoryPage.sortDropdown);
        SeleniumCommands.clickOnElement(inventoryPage.options().get(3));

        List<Double> actualPrices = inventoryPage.itemPrices.stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices.get(0), sortedPrices.get(0));
        Assert.assertEquals(actualPrices.get(actualPrices.size() - 1), sortedPrices.get(sortedPrices.size() - 1));
    }

    @Test
    public void verifySortingResetAfterRefresh() {
        SeleniumCommands.clickOnElement(inventoryPage.sortDropdown);
        SeleniumCommands.clickOnElement(inventoryPage.options().get(2));

        Assert.assertEquals(inventoryPage.itemPrices.get(0).getText(), Data.LOWEST_PRICE);
        driver.navigate().refresh();
        Assert.assertEquals(inventoryPage.itemTitles.get(0).getText(), Data.FIRST_PRODUCT_NAME);
    }

    @Test
    public void clickingTwitterIconNavigatesToTwitterProfile() {
        SeleniumCommands.clickOnElement(inventoryPage.twitterIcon);

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Assert.assertEquals(driver.getCurrentUrl(), Data.TWITTER);
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void clickingFacebookIconNavigatesToTwitterProfile() {
        SeleniumCommands.clickOnElement(inventoryPage.facebookIcon);

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Assert.assertEquals(driver.getCurrentUrl(), Data.FACEBOOK);
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void clickingLinkedinIconNavigatesToTwitterProfile() {
        SeleniumCommands.clickOnElement(inventoryPage.linkedinIcon);

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Assert.assertEquals(driver.getCurrentUrl(), Data.LINKEDIN);
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void hamburgerIconOpensUpTheMenu() {
        SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
        Assert.assertEquals(inventoryPage.hamburgerMenu.getAttribute("aria-hidden"), "false");
    }



//    @Test
//    public void showMustGoOn() throws InterruptedException {
//        System.out.println(inventoryPage.twitterIcon);
//    }

    @Test
    public void allItemsLinkNavigatesToInventoryPage() throws InterruptedException {
        SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
        Thread.sleep(2000);
        SeleniumCommands.clickOnElement(hamburgerMenuPage.allItemsLink);

        Assert.assertEquals(driver.getCurrentUrl(), Data.INVENTORY_PAGE);
    }

    @Test
    public void aboutLinkNavigatesToSauceLabs() throws InterruptedException {
        SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
        Thread.sleep(2000);
        SeleniumCommands.clickOnElement(hamburgerMenuPage.aboutLink);

        Assert.assertEquals(driver.getCurrentUrl(), Data.SAUCE_LABS);
    }

    @Test
    public void logutLinkLogsOutTheUser() throws InterruptedException {
        SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
        Thread.sleep(2000);
        SeleniumCommands.clickOnElement(hamburgerMenuPage.logutLink);

        Assert.assertEquals(driver.getCurrentUrl(), Data.URL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void resetLinkResetsTheAppState() throws InterruptedException {
        SeleniumCommands.clickOnElement(inventoryPage.addToCartButtons.get(0));
        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        SeleniumCommands.clickOnElement(inventoryPage.hamburgerMenuIcon);
        Thread.sleep(2000);
        SeleniumCommands.clickOnElement(hamburgerMenuPage.resetLink);

        List<WebElement> badges = driver.findElements(By.cssSelector("[data-test='shopping-cart-badge']"));
        Assert.assertTrue(badges.isEmpty());
    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
