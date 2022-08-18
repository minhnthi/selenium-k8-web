package test_flows.global;

import models.components.global.TopMenuComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(driver);
        FooterComponent footerComponent = basePage.footerComponent();
        verifyInformationColumn(footerComponent.informationColumnComponent());
        verifyCustomerServiceColumn(footerComponent.customerServiceColumnComponent());
        verifyAccountColumn(footerComponent.myAccountColumnComponent());
        verifyFollowUsColumn(footerComponent.followUsColumnComponent());
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                "Sitemap",
                "Shipping & Returns",
                "Privacy Notice",
                "Conditions of Use",
                "About us",
                "Contact us"
        );
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/sitemap",
                baseUrl + "/shipping-returns",
                baseUrl + "/privacy-policy",
                baseUrl + "/conditions-of-use",
                baseUrl + "/about-us",
                baseUrl + "/contactus"
        );
        verifyFooterComponent(footerColumnComponent, expectedLinkTexts, expectedHrefs);

    }

    private void verifyCustomerServiceColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                "Search",
                "News",
                "Blog",
                "Recently viewed products",
                "Compare products list",
                "New products");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/search",
                baseUrl + "/news",
                baseUrl + "/blog",
                baseUrl + "/recentlyviewedproducts",
                baseUrl + "/compareproducts",
                baseUrl + "/newproducts");

        verifyFooterComponent(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                "My account",
                "Orders",
                "Addresses",
                "Shopping cart",
                "Wishlist"
        );
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/customer/info",
                baseUrl + "/customer/orders",
                baseUrl + "/customer/addresses",
                baseUrl + "/cart",
                baseUrl + "/wishlist"
        );
        verifyFooterComponent(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Facebook",
                "Twitter",
                "RSS",
                "YouTube",
                "Google+"
        );
        List<String> expectedHrefs = Arrays.asList(
                "http://www.facebook.com/nopCommerce",
                "https://twitter.com/nopCommerce",
                "http://demowebshop.tricentis.com/news/rss/1",
                "http://www.youtube.com/user/nopCommerce",
                "https://plus.google.com/+nopcommerce"
        );
        verifyFooterComponent(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    public void verifyProductCatFooterComponent() {
        // Random pickup an item
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComponent = basePage.topMenuComponent();
        List<TopMenuComponent.MainCatItem> mainCatElem = topMenuComponent.mainCatItemsElem();
        if (mainCatElem.isEmpty()) {
            Assert.fail("[ERR] There is no item on the top menu");
        }
        TopMenuComponent.MainCatItem randomMainItemElem = mainCatElem.get(new SecureRandom().nextInt(mainCatElem.size()));
        //  TopMenuComponent.MainCatItem randomMainItemElem = mainCatElem.get(1);
        String randomCatHref = randomMainItemElem.catItemLinkElem().getAttribute("href");

        // Get sub-list
        List<TopMenuComponent.SublistComponent> sublistComponents = randomMainItemElem.sublistComponents();

        if (sublistComponents.isEmpty()) {
            randomMainItemElem.catItemLinkElem().click();
        } else {
            int randomIndex = new SecureRandom().nextInt(sublistComponents.size());
            TopMenuComponent.SublistComponent randomSublistComponent = sublistComponents.get(randomIndex);
            randomCatHref = randomSublistComponent.getComponent().getAttribute("href");
            randomSublistComponent.getComponent().click();
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains(randomCatHref));
        } catch (TimeoutException e) {
            Assert.fail("[ERR] Target page is not matched! ");
        }

        // Verify Footer Component
        verifyFooterComponent();
    }

    private static void verifyFooterComponent(FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs) {

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        for (WebElement link : footerColumnComponent.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }
        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("[ERR] Text or hyperlink is empty in footer column!");
        }

        // Verify Link Text
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "TEXT [ERR] Actual and expected link texts are different!");

        // Verify Href
        Assert.assertEquals(actualHrefs, expectedHrefs, "LINK [ERR] Actual and expected hyperlink are different!");

    }
}
