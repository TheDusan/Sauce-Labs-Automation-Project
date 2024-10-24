package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.LongStream;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test='shopping-cart-link']")
    public WebElement shoppingCartIcon;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    public WebElement shoppingCartBadge;

    @FindBy(id="react-burger-menu-btn")
    public WebElement hamburgerMenuIcon;

    @FindBy(className = "inventory_item")
    public List<WebElement> items;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemTitles;

    @FindBy(className = "inventory_item_img")
    public List<WebElement> itemImages;

    @FindBy(css = "[data-test='product-sort-container']")
    public WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrices;

    @FindBy(css = "[data-test='social-twitter']")
    public WebElement twitterIcon;

    @FindBy(css = "[data-test='social-facebook']")
    public WebElement facebookIcon;

    @FindBy(css = "[data-test='social-linkedin']")
    public WebElement linkedinIcon;

    @FindBy(css = "div.bm-menu-wrap")
    public WebElement hamburgerMenu;

    // methods
    public String itemName(int num) {
        return items.get(num).getText();
    }

    public List<WebElement> options() {
        Select select = new Select(sortDropdown);
        return select.getOptions();
    }
}
