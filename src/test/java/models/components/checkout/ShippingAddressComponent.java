package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCssSelector("#opc-shipping")
public class ShippingAddressComponent extends Component {

    private final static By shippingAddressDropdownSel = By.id("shipping-address-select");
    private final  static By firstnameSel = By.id("ShippingNewAddress_FirstName");
    private final  static By lastnameSel = By.id("ShippingNewAddress_LastName");
    private final  static By emailSel = By.id("ShippingNewAddress_Email");
    private final  static By countryDropdownSel = By.id("ShippingNewAddress_CountryId");
    private final  static By stateLoadingSel = By.id("states-loading-progress");
    private final  static By stateDropdownSel = By.id("ShippingNewAddress_StateProvinceId");
    private final  static By citySel = By.id("ShippingNewAddress_City");
    private final  static By address1Sel = By.id("ShippingNewAddress_Address1");
    private final  static By zipcodeSel = By.id("ShippingNewAddress_ZipPostalCode");
    private final  static By phoneSel = By.id("ShippingNewAddress_PhoneNumber");
    private final static By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectNewShippingAddress(){
        if (!component.findElements(shippingAddressDropdownSel).isEmpty()) {
            Select select = new Select(component.findElement(shippingAddressDropdownSel));
            select.selectByVisibleText("New Address");
        }
    }
    public void inputFirstName(String firstname){
        component.findElement(firstnameSel).sendKeys(firstname);
    }
    public  void inputLastName(String lastname){
        component.findElement(lastnameSel).sendKeys(lastname);
    }
    public  void inputEmail(String email){
        component.findElement(emailSel).sendKeys(email);
    }
    public void selectCountry(String country){
        Select select = new Select(component.findElement(countryDropdownSel));
        select.selectByVisibleText(country);
        wait.until(ExpectedConditions.invisibilityOf(component.findElement(stateLoadingSel)));
        try {
            Thread.sleep(3000);
        }catch (Exception ignored){}
    }
    public  void selectState(String state){
        Select select = new Select(component.findElement(stateDropdownSel));
        select.selectByVisibleText(state);
    }
    public  void inputCity(String city){
        component.findElement(citySel).sendKeys(city);
    }
    public void inputAddress1(String address1){
        component.findElement(address1Sel).sendKeys(address1);
    }
    public void inputZipCode(String zipcode){
        component.findElement(zipcodeSel).sendKeys(zipcode);
    }
    public void inputPhone(String phone){
        component.findElement(phoneSel).sendKeys(phone);
    }
    public void clickOnContinueBtn(){
        WebElement continueButton = component.findElement(continueBtnSel);
        continueButton.click();
        wait.until(ExpectedConditions.invisibilityOf(continueButton));
    }
}
