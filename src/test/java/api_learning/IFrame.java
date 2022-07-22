package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame implements Urls {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try{
           // Navigate to target page
            driver.get(baseUrl.concat(iframeSlug));
            // Locate the iframe
            By iframeSel = By.cssSelector("[id$='ifr']");
            WebElement iframeElem = driver.findElement(iframeSel);

            // Switch to the iframe
            driver.switchTo().frame(iframeElem);

            // Locate element inside  the iframe
            WebElement editorInputElem = driver.findElement(By.id("tinymce"));
            editorInputElem.click();
            editorInputElem.clear();
            editorInputElem.sendKeys("This is the new text value...");

            // Switch back parent frame
            //driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();

            Thread.sleep(1000);

        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
