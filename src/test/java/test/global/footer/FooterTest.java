package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import support.verification.Verifier;
import url.Urls;

public class FooterTest {
    @Test(priority = 1, dependsOnMethods = {"testFooterRegisterPage"})
    public void testFooterCategoryPage() {
    }

    @Test(priority = 2)
    public void testFooterRegisterPage() {
        String actualResult = "teo";
        String expectedResult = "ti";
        //Verifier.verifyEquals(actualResult,expectedResult);

        // Hard Assertion
        Assert.assertEquals(actualResult,expectedResult,"[ERR] Welcome message is incorrect!");
        Assert.assertTrue(actualResult.equals(expectedResult), "[ERR] ... ");
        Assert.assertFalse(actualResult.equals(expectedResult), "[ERR] ... ");
        Assert.fail();
        Assert.fail("[ERR]...");
    }

    @Test(priority = 3)
    public void testFooterLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertEquals(true,true);
        softAssert.assertEquals(3,2);

        softAssert.assertAll();

        System.out.println("Hello");
    }
    // @Test(priority = 4)
    public void testFooterHomePage() {
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);
        try {
            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComponent =
                    homePage.footerComponent().informationColumnComponent();
            System.out.println(informationColumnComponent.headElem().getText());
            testFooterColumn(informationColumnComponent);

            CustomerServiceColumnComponent customerServiceColumnComponent =
                    homePage.footerComponent().customerServiceColumnComponent();
            testFooterColumn(customerServiceColumnComponent);
        } catch (Exception ignored) {
            driver.quit();
        }
        driver.quit();
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }

}
