package test.global.footer;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {
    @Test()
    public void testFooterCategoryPage() {
        driver.get(Urls.demoBaseUrl);
        Assert.fail("Demo taking screenshot when is failed");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }

    @Test()
    public void testFooterHomePage() {
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }
}
