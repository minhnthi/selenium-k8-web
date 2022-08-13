package models.pages;

import models.components.Component;
import models.components.global.TopMenuComponent;
import models.components.global.footer.FooterComponent;
import models.components.product.ProductGridComponent;
import models.components.product.ProductItemComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage extends Component {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public TopMenuComponent topMenuComponent(){
        return findComponent(TopMenuComponent.class,driver);
    }

    public ProductGridComponent productGridComponent(){
        return findComponent(ProductGridComponent.class,driver);
    }
    public FooterComponent footerComponent(){
        return  findComponent(FooterComponent.class,driver);
    }
}
