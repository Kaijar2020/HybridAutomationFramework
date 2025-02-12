package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration(){
        try {
            logger.info("******Starting The Test Cases*******");
            HomePage homePage = new HomePage(driver);

            homePage.clickMyAccount();
            logger.info("**Click on My Account Link**");

            homePage.clickRegister();
            logger.info("**Click on Register**");

            AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

            logger.info("**Providing Customer Details**");
            accountRegistrationPage.setFastName(random_string(3));
            accountRegistrationPage.setLastName(random_string(5));
            accountRegistrationPage.setEmail(random_string(5) + "@gmail.com");
            accountRegistrationPage.setPhone(random_number(10));

            //Set Password.
            String pass = random_alpha_numeric(3);
            accountRegistrationPage.setPassword(pass);
            accountRegistrationPage.setConfirmPassword(pass);

            accountRegistrationPage.setPrivacyPolicy();
            accountRegistrationPage.clickContinue();

            logger.info("***Validating expected message.***");
            String confirm_msg = accountRegistrationPage.getConfirmationMsg();
            String expected_msg = "Your Account Has Been Created!";
            Assert.assertEquals(confirm_msg, expected_msg);
        }
        catch (Exception e){
            logger.error("**Test Failed**");
            logger.debug("***The Debug Logs..***");
            Assert.assertTrue(false);
        }
        logger.info("***Test is Finished***");
    }

}
