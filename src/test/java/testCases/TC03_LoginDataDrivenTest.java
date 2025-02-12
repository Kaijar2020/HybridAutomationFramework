package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilites.DataProviders;

public class TC03_LoginDataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "datadriven")
    public void verify_login_with_DDT(String email, String pass, String result){
        logger.info("**Started TC_03 Login Data Driven test**");
        HomePage homePage= new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.writeEmail(email);
        loginPage.writePassword(pass);
        loginPage.clickLogin();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        boolean target_Page = myAccountPage.isMyAccountPageExist();

        //validation.
        if (result.equalsIgnoreCase("valid")){
            if (target_Page == true){
                Assert.assertTrue(true);
                myAccountPage.clickLogout();
            }
            else {
                Assert.assertTrue(false);
            }
        }
        if (result.equalsIgnoreCase("Invalid")){
            if (target_Page == true){
                myAccountPage.clickLogout();
                Assert.assertTrue(false);
            }
            else {
                Assert.assertTrue(true);
            }
        }

        logger.info("**Test Finished**");
    }
}
