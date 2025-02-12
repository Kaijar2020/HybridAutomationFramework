package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id= "input-email")
//    @FindBy(xpath = "//input[@id='input-email']")
    WebElement insertEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement insertPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    public void writeEmail(String email){
        insertEmail.sendKeys(email);
    }

    public void writePassword(String pass){
        insertPassword.sendKeys(pass);
    }

    public void clickLogin(){
        btnLogin.click();
    }
}
