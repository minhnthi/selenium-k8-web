package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;
import java.util.List;

public class JsAlerts implements Urls {

    private final static By jsAlertSel = By.cssSelector("[onclick=\"jsAlert()\"]");
    private final static By jsAlertConfirmSel = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private final static By jsAlertPromptSel = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private final static By resultSel = By.cssSelector("#result");

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target page
            driver.get(baseUrl.concat(alertsSlug));

            //JS_ALERT | OK
            handleAlert(driver, jsAlertSel, true);
            System.out.println("Result: " + driver.findElement(resultSel).getText());

            //JS_CONFIRM | Cancel
            handleAlert(driver, jsAlertConfirmSel, false);
            System.out.println("Result: " + driver.findElement(resultSel).getText());

            //JS_PROMPT
            handleAlert(driver, jsAlertPromptSel, "My Name is Mia !!!");
            System.out.println("Result: " + driver.findElement(resultSel).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public static void handleAlert(WebDriver driver, By triggerAlertSel, boolean isAccepting) {
        Alert alert = getAlert(driver, triggerAlertSel);
        System.out.println("Alert Content: " + alert.getText());
        if (isAccepting) alert.accept();
        else alert.dismiss();
    }

    public static void handleAlert(WebDriver driver, By triggerAlertSel, String contentToEnter) {
        Alert alert = getAlert(driver, triggerAlertSel);
        System.out.println("Alert Content: " + alert.getText());
        alert.sendKeys(contentToEnter);
        alert.accept();
    }

    private static Alert getAlert(WebDriver driver, By triggerAlertSel) {
        WebElement triggerJsAlertBtnElm = driver.findElement(triggerAlertSel);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        triggerJsAlertBtnElm.click();
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
