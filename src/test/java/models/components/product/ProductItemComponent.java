package models.components.product;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".product-item")
public class ProductItemComponent extends Component {

    private static  final By productTitleSel = By.cssSelector(".product-title");

    public ProductItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public  WebElement productTitleElem(){
        return component.findElement(productTitleSel);
    }
}
