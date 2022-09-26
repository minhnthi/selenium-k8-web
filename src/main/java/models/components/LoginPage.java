package models.components;

public abstract class LoginPage {

    public abstract String username();
    public abstract String password();
    public abstract String loginBtn();

    public void login(){
        System.out.println(username());
        System.out.println(password());
        System.out.println(loginBtn());
    }

    public void verifyLoginSuccess(){
        System.out.println(("Verifying Login Dashboard!"));

    }
}
