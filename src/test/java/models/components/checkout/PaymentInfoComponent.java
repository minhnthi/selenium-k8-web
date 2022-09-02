package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInfoComponent extends Component {
    public PaymentInfoComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
