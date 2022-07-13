package api_learning;

import dev.failsafe.internal.util.Assert;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteractionMultipleMatching {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector value
            By loginInputFieldsSel = By.tagName("input");

            //Interaction
            List<WebElement> loginFormFieldsElem = driver.findElements(loginInputFieldsSel);
            final int USERNAME_INDEX =0;
            final int PASSWORD =1;
            if (!loginFormFieldsElem.isEmpty()) {
                loginFormFieldsElem.get(USERNAME_INDEX).sendKeys("test.com");
                loginFormFieldsElem.get(PASSWORD).sendKeys("1234");
            }else {
                //Assert.fail("");
            }
            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  Quit the browser session
        driver.quit();

        //Implicit wait, explicit, Fluent wait
    }
}
