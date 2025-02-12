package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    //Locators.
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telePhone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement checkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueBtn;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmed;

    //Action methods.
    public void setFastName(String fname){
        firstName.sendKeys(fname);
    }

    public void setLastName(String lname){
        lastName.sendKeys(lname);
    }

    public void setEmail(String emaill){
        email.sendKeys(emaill);
    }

    public void setPhone(String phone){
        telePhone.sendKeys(phone);
    }

    public void setPassword(String pass){
        password.sendKeys(pass);
    }

    public void setConfirmPassword(String pass){
        confirmPassword.sendKeys(pass);
    }

    public void setPrivacyPolicy(){
        checkPolicy.click();
    }

    public void clickContinue(){
        continueBtn.click();
    }

    public String getConfirmationMsg(){
        try{
            return (msgConfirmed.getText());
        }
        catch (Exception e){
            return (e.getMessage());
        }
    }
}
