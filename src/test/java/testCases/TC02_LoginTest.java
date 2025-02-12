package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;



public class TC02_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_login(){
        logger.info("**TC_02***Login Test Started***");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.writeEmail("cosmos79@yopmail.com");
            loginPage.writePassword("123456!!abcd");
            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean target_Page = myAccountPage.isMyAccountPageExist();
            Assert.assertTrue(target_Page);
        }
        catch (Exception e){
            Assert.fail();
        }

        logger.info("**Finished the TC_02 Test**");
    }
}
