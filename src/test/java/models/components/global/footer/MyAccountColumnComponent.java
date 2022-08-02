package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".column.my-account")
public class MyAccountColumnComponent extends FooterColumnComponent{

    public MyAccountColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
