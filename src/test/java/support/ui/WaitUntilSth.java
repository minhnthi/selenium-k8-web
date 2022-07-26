package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitUntilSth implements ExpectedCondition<Boolean> {

    private By cssSel;

    public WaitUntilSth(By cssSel) {
        this.cssSel = cssSel;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return driver.findElement(cssSel).getText().equals("Mia");
    }
}
