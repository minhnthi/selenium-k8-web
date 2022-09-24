package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.*;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutOptionsPage;
import models.pages.CheckoutPage;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.shipping.ShippingDataObject;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private final int quantity;

    private double totalItemPrice;
    private UserDataObject defaultCheckoutUser;
    private ShippingDataObject defaultCheckoutShippingAddress;
    private PaymentMethod paymentMethod;
    private CreditCardType creditCardType;


    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity, double totalItemPrice) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
        this.totalItemPrice = totalItemPrice;
    }

    public void buildCompSpecAndAddToCart() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComponent(computerEssentialComponent);

        // Unselect all default optons
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);

        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);

        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {
            String fullOsStr = computerEssentialComp.selectOS(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);
        }
        String fullSoftwareStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extractAdditionalPrice(fullSoftwareStr);

        // Calculate item's price
        double basePrice = computerEssentialComp.productPrice();
        double allAdditionalPrices =
                processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + additionalOsPrice + additionalSoftwarePrice;
        totalItemPrice = (basePrice + allAdditionalPrices) * quantity;

        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilAddedToCart();

        // Navigate to shopping cart
        computerItemDetailPage.headerComponent().clickOnShoppingCartLink();
    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComponents = shoppingCartPage.cartItemRowComponents();
        if (cartItemRowComponents.isEmpty()) {
            Assert.fail("[ERR] There is no item displayed in the shopping cart!");
        }

        double currentSubtotal = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComponent : cartItemRowComponents) {
            currentSubtotal = currentSubtotal + cartItemRowComponent.subTotal();
            currentTotalUnitPrices = currentTotalUnitPrices + (cartItemRowComponent.unitPrice() * cartItemRowComponent.quantity());
        }

        Assert.assertEquals(currentSubtotal, currentTotalUnitPrices,
                "[ERR] Shopping cart's sub-total is incorrect!");

        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;

        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }
        Assert.assertEquals(checkoutSubTotal, currentSubtotal, "[ERR] Current sub-total different checkout sub-total");
        Assert.assertEquals(checkoutTotal, (checkoutTotal + checkoutOtherFeesTotal), "[ERR] Current checkout total different checkout total");
    }

    public void agreeTOSAndCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComponent().agreeTOS();
        shoppingCartPage.totalComponent().clickOnCheckOutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();
    }

    public void inputBillingAddress() {
        String defaultCheckoutUserJSONLoc = "/src/test/java/test_data/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserDataObject.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComponent = checkoutPage.billingAddressComponent();
        billingAddressComponent.selectInputNewAddress();
        billingAddressComponent.inputFirstname(defaultCheckoutUser.getFirstname());
        billingAddressComponent.inputLastname(defaultCheckoutUser.getLastname());
        billingAddressComponent.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComponent.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComponent.selectState(defaultCheckoutUser.getState());
        billingAddressComponent.inputCity(defaultCheckoutUser.getCity());
        billingAddressComponent.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComponent.inputZIPCode(defaultCheckoutUser.getZipCode());
        billingAddressComponent.inputPhoneNo(defaultCheckoutUser.getPhoneNum());
        billingAddressComponent.clickOnContinueBtn();
    }

    public void inputShippingAddress() {
        String defaultShippingAddressJSONLoc = "/src/test/java/test_data/DefaultCheckoutShippingAddress.json";
        defaultCheckoutShippingAddress = DataObjectBuilder.buildDataObjectFrom(defaultShippingAddressJSONLoc,ShippingDataObject.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingAddressComponent shippingAddressComponent = checkoutPage.shippingAddressComponent();
        shippingAddressComponent.selectNewShippingAddress();
        shippingAddressComponent.inputFirstName(defaultCheckoutShippingAddress.getFirstname());
        shippingAddressComponent.inputLastName(defaultCheckoutShippingAddress.getLastname());
        shippingAddressComponent.inputEmail(defaultCheckoutShippingAddress.getEmail());
        shippingAddressComponent.selectCountry(defaultCheckoutShippingAddress.getCountry());
        shippingAddressComponent.selectState(defaultCheckoutShippingAddress.getState());
        shippingAddressComponent.inputAddress1(defaultCheckoutShippingAddress.getAdd1());
        shippingAddressComponent.inputCity(defaultCheckoutShippingAddress.getCity());
        shippingAddressComponent.inputZipCode(defaultCheckoutShippingAddress.getZipCode());
        shippingAddressComponent.inputPhone(defaultCheckoutShippingAddress.getPhoneNum());
       shippingAddressComponent.clickOnContinueBtn();
    }

    public void selectShippingMethod() {
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComponent = checkoutPage.shippingMethodComponent();
        shippingMethodComponent.selectShippingMethod(randomShippingMethod).clickOnContinueButton();
        // shippingMethodComponent.clickOnContinueButton();

    }

    public void selectPaymentMethod() {
        this.paymentMethod = PaymentMethod.COD;
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("[ERR] Payment method can't be null ! ");
        }
        this.paymentMethod = paymentMethod;

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComponent = checkoutPage.paymentMethodComponent();
        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComponent.selectCheckMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComponent.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComponent.selectPurchaseOrderMethod();
                break;
            default:
                paymentMethodComponent.selectCODMethod();
        }
        paymentMethodComponent.clickOnContinueBtn();

    }

    public void inputPaymentInfo(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentInfoComponent paymentInfoComponent = checkoutPage.paymentInfoComponent();

        if (this.paymentMethod.equals(PaymentMethod.PURCHASE_ORDER)) {
            // This can be dynamic as well
            paymentInfoComponent.inputPurchaseNum("123");
        }

        if (this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            paymentInfoComponent.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckoutUser.getFirstname();
            String cardHolderLastName = this.defaultCheckoutUser.getLastname();
            paymentInfoComponent.inputCardHolderName(cardHolderFirstName + "" + cardHolderLastName);
            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" : "6011000990139424";
            paymentInfoComponent.inputCardNumber(cardNumber);
            // Select current month and next year
            Calendar calendar = new GregorianCalendar();
            paymentInfoComponent.inputExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            paymentInfoComponent.inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
            paymentInfoComponent.inputCardCode("123");
            paymentInfoComponent.clickOnContinueBtn();
        }
    }

    public void confirmOrder() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ConfirmOrderComponent confirmOrderComponent = checkoutPage.confirmOrderComponent();
        confirmOrderComponent.clickOnConfirmBtn();
        try {
            Thread.sleep(3000);
        } catch (Exception ignored){}
    }
}
