package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementDisplaying {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();
        // Navigate to the target page
        driver.get("https://the-internet.herokuapp.com/login");

        //List<WebElement> elementList = driver.findElement(By.tagName("test"));
        List<WebElement> elementList = driver.findElements(By.tagName("test"));
        if (!elementList.isEmpty()) {
           //Assert.
        }
        //  Quit the browser session
        driver.quit();

        //Implicit wait, explicit, Fluent wait
    }
}
