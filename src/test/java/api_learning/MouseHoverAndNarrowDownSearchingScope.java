package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDownSearchingScope implements Urls {

    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target page
            driver.get(baseUrl.concat(hoverSlug));

             /*// Normal finding element
            List<WebElement> figcaptionNames = driver.findElements(By.cssSelector(".figcaption h5"));
            List<WebElement> figcaptionHrefs = driver.findElements(By.cssSelector(".figcaption a"));*/

            // Target parent elements
            List<WebElement> figuresElem = driver.findElements(figureSel);
            if (figuresElem.isEmpty())
                throw new RuntimeException("There is no profile image displayed !");

            // Define actions object
            Actions actions = new Actions(driver);

            for (WebElement figureElem : figuresElem) {
                WebElement profileNameElem = figureElem.findElement(profileNameSel);
                WebElement profileLinkElem = figureElem.findElement(profileLinkSel);

                // Before mouse hover
                System.out.println(profileNameElem.getText() + " : " + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + " : " + profileLinkElem.isDisplayed());

                // Mouse hover
                actions.moveToElement(figureElem).perform();

                // after mouse hover
                System.out.println(profileNameElem.getText() + " : " + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + " : " + profileLinkElem.isDisplayed());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
