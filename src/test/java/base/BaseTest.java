package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseTest {

    public static WebDriver driver;
    public HomepagePage homepagePage;
    public InventoryPage inventoryPage;
    public HamburgerMenuPage hamburgerMenuPage;
    public CartPage cartPage;
    public CheckoutFirstPage checkoutFirstPage;
    public CheckoutSecondPage checkoutSecondPage;
    public CheckoutCompletePage checkoutCompletePage;
    public Connection connection;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        this.homepagePage = new HomepagePage();
        this.inventoryPage = new InventoryPage();
        this.hamburgerMenuPage = new HamburgerMenuPage();
        this.cartPage = new CartPage();
        this.checkoutFirstPage = new CheckoutFirstPage();
        this.checkoutSecondPage = new CheckoutSecondPage();
        this.checkoutCompletePage = new CheckoutCompletePage();

    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
