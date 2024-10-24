package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerMenuPage extends BaseTest {

    public HamburgerMenuPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[data-test='inventory-sidebar-link']")
    public WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    public WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logutLink;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetLink;
}
