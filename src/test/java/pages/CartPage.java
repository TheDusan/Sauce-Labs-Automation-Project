package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeButtons;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    @FindBy(css = "[data-test='inventory-item-price']")
    public List<WebElement> itemPrices;

    @FindBy(css = "[data-test='inventory-item-name']")
    public List<WebElement> itemNames;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;
}
