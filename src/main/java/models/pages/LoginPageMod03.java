package models.pages;

import models.components.LoginFormComponent;
import org.openqa.selenium.WebDriver;

public class LoginPageMod03 {

    private final WebDriver driver;

    public LoginPageMod03(WebDriver driver) {
        this.driver = driver;
    }

    public LoginFormComponent loginFormComponent(){
        return  new LoginFormComponent(driver);
    }
}
