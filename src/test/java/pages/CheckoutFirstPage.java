package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutFirstPage extends BaseTest {

    public CheckoutFirstPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameInputField;

    @FindBy(id = "last-name")
    public WebElement lastNameInputField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeInputField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(css = "h3[data-test='error']")
    public WebElement errorField;

}
