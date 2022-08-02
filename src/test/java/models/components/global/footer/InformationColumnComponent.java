package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".column.information")
public class InformationColumnComponent extends FooterColumnComponent{

    public InformationColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
