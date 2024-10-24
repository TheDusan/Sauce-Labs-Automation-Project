package utils;

import org.openqa.selenium.WebElement;


// posebna klasa sa statickim metodama
// za stvari koje se ponavljaju u svakoj klasi
public class SeleniumCommands {

    public static void sendKeys(WebElement element, String input) {
        element.sendKeys(input);

    }

    public static void clickOnElement(WebElement element) {
        element.click();
    }
}
