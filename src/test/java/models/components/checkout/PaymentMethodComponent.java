package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector("#opc-payment_method")
public class PaymentMethodComponent extends Component {
    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
