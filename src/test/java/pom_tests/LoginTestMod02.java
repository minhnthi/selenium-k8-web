package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod02;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod02 implements Urls {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target page
            driver.get(baseUrl.concat(loginSlug));
            LoginPageMod02 loginPage = new LoginPageMod02(driver);
            loginPage.inputUsername("Mia");
            loginPage.inputPassword("123");
            loginPage.ClickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
