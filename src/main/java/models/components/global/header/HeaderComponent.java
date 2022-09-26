package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".header")
public class HeaderComponent  extends Component {
    private final static By shoppingCartLinkSel = By.cssSelector("#topcartlink");
    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void clickOnShoppingCartLink(){
        WebElement shopCartLinkElem = component.findElement(shoppingCartLinkSel);
        scrollUpToElement(shopCartLinkElem);
        shopCartLinkElem.click();
    }
}
