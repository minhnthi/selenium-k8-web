package support.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitMoreThanOneTab implements ExpectedCondition<Boolean> {

    @Override
    public Boolean apply(WebDriver driver) {
        return driver.getWindowHandles().size() > 1;
    }

    @Override
    public String toString() {
        return " tab numbers to be bigger than 2 ! " ;
    }
}
