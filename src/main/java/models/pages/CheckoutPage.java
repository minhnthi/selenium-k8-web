package models.pages;

import models.components.checkout.*;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComponent(){
        return findComponent(BillingAddressComponent.class,driver);
    }
    public ShippingAddressComponent shippingAddressComponent(){
        return findComponent(ShippingAddressComponent.class,driver);
    }
    public ShippingMethodComponent shippingMethodComponent(){
        return findComponent(ShippingMethodComponent.class,driver);
    }
    public PaymentMethodComponent paymentMethodComponent(){
        return findComponent(PaymentMethodComponent.class,driver);
    }
    public PaymentInfoComponent paymentInfoComponent(){
        return findComponent(PaymentInfoComponent.class,driver);
    }
    public ConfirmOrderComponent confirmOrderComponent(){
        return findComponent(ConfirmOrderComponent.class,driver);
    }
}
