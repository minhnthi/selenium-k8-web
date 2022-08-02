package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class FooterTest {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            testFooterHomePage(driver);
            testFooterCategoryPage(driver);
            testFooterRegisterPage(driver);
            testFooterLoginPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    private static void testFooterHomePage(WebDriver driver) {
        driver.get(Urls.demoBaseUrl);
        HomePage homePage = new HomePage(driver);
        InformationColumnComponent informationColumnComponent =
                homePage.footerComponent().informationColumnComponent();
        System.out.println(informationColumnComponent.headElem().getText());
        testFooterColumn(informationColumnComponent);

        CustomerServiceColumnComponent customerServiceColumnComponent =
                homePage.footerComponent().customerServiceColumnComponent();
        testFooterColumn(customerServiceColumnComponent);
    }

    private static void testFooterCategoryPage(WebDriver driver) {
    }

    private static void testFooterRegisterPage(WebDriver driver) {
    }

    private static void testFooterLoginPage(WebDriver driver) {
    }

    public static void testFooterColumn(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }


}
