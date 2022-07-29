package pom_tests;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageMod02;
import models.pages.LoginPageMod03;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod03 implements Urls {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target page
            driver.get(baseUrl.concat(loginSlug));
            LoginPageMod03 loginPage = new LoginPageMod03(driver);
            LoginFormComponent loginFormCom = new LoginFormComponent(driver);

            loginFormCom.inputUsername("Mia");
            loginFormCom.inputPassword("123");
            loginFormCom.ClickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
