package models.components.product;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCssSelector(value = ".product-grid")
public class ProductGridComponent extends Component {
    public ProductGridComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<ProductItemComponent> productItemComponents (){
        return  findComponents(ProductItemComponent.class,driver);
    }
}
