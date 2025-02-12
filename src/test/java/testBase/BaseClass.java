package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties pro;

    @BeforeClass(groups = {"Sanity","Regression","Master"})
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException {

        //Loading Properties file.
        FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
        Properties pro = new Properties();
        pro.load(file);

        //Set Log
        logger= LogManager.getLogger(this.getClass());

        //For parallel testing in multiple browser.
        switch (browser.toLowerCase()){
            case "chrome" : driver = new ChromeDriver();
                            break;
            case "firefox" : driver = new FirefoxDriver();
                            break;
            case "edge" : driver = new EdgeDriver();
                            break;
            default: System.out.println("Invalid browser name.");
                            return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(pro.getProperty("app_url1"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity","Regression","Master"})
    public void tear_down(){
        driver.quit();
    }

    public String random_string(int c){
        String generate = RandomStringUtils.randomAlphabetic(c);
        return generate;
    }

    public String random_number(int c){
        String generate = RandomStringUtils.randomNumeric(c);
        return generate;
    }

    public String random_alpha_numeric(int c){
        String generate1 = RandomStringUtils.randomAlphabetic(c);
        String generate2 = RandomStringUtils.randomNumeric(c);
        return (generate1+"!!"+generate2);
    }

    public String capture_screen(String test_name){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilepath = System.getProperty("user.dir")+"/screenshots/"+test_name+"_"+timeStamp+".png";
        File targetFile = new File(targetFilepath);
        sourceFile.renameTo(targetFile);

        return targetFilepath;

    }
}
