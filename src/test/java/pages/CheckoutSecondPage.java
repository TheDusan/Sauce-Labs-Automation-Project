package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutSecondPage extends BaseTest {

    public CheckoutSecondPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[data-test='inventory-item-price']")
    public List<WebElement> itemPrices;

    @FindBy(css = "div[data-test='subtotal-label']")
    public WebElement totalPrice;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

}
