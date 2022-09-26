package models.pages;

import models.components.Component;
import models.components.global.TopMenuComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.header.HeaderComponent;
import models.components.product.ProductGridComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends Component {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }
    public HeaderComponent headerComponent(){
       return findComponent(HeaderComponent.class, driver);
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
