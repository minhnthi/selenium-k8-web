package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;
import url.Urls;

public class Dropdown implements Urls {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try{
           // Navigate to target page
            driver.get(baseUrl.concat(dropDownSlug));

            // Dropdown locator and element
            By dropdownSel = By.id("dropdown");
            WebElement dropdownElem = driver.findElement(dropdownSel);

            // Select dropdown
            SelectEx select = new SelectEx(dropdownElem);

            // Select by visible text | Option 1
            select.selectOption1();
            Thread.sleep(1000);

            // Select by index | Option 2
            select.selectByIndex(2);
            Thread.sleep(1000);

            // Select by value | Option 1
            select.selectByValue("1");
            Thread.sleep(1000);




        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
