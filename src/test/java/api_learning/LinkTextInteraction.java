package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkTextInteraction {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector value
            //By poweredByLinkTextSel = By.linkText("Elemental Selenium");
            By poweredByLinkTextSel = By.partialLinkText("Elemental");


            // 2. Find elements
            WebElement poweredByLinkTextElem = driver.findElement(poweredByLinkTextSel);

            // Interaction
            poweredByLinkTextElem.click();


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
