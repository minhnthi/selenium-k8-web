package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;
import support.ui.WaitUntilSth;
import url.Urls;

import java.time.Duration;

public class ExplicitWait implements Urls {

    public static void main(String[] args) {

        // Get a chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target page
            driver.get(baseUrl.concat(loginSlug));

            // Dropdown locator and element
            By testSel = By.cssSelector("#test");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(testSel));
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(testSel)));
            wait.until(new WaitMoreThanOneTab());
          //  wait.until(new WaitUntilSth(By.cssSelector("Miiiiiaaaa")));
            // Windows / Tabs ----> waitUntil >1



        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
