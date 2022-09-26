package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod01 implements Urls {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target page
            driver.get(baseUrl.concat(loginSlug));
            LoginPageMod01 loginPage = new LoginPageMod01(driver);
            loginPage.username().sendKeys("Mia");
            loginPage.password().sendKeys("123");
            loginPage.loginBtn().click();

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
