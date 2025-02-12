package utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ExtentReportsManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; //UI of the reports.
    public ExtentReports extent; //common info on the project.
    public ExtentTest test; //creating test case entire on the report and update status on test methods.

    String reportName;

    public void onStart(ITestContext context){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report_"+timeStamp+".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+reportName); //specific test report folder.

        sparkReporter.config().setDocumentTitle("Opencart automation report."); //Title of the Report.
        sparkReporter.config().setReportName("Opencart webapp test");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter); //Attach the repo

        extent.setSystemInfo("Application","Opencart WebApp");
        extent.setSystemInfo("Computer Name","localhost");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));

        String OS = context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("OS name",OS);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser name",browser);

        List<String>include_group = context.getCurrentXmlTest().getIncludedGroups();
        if (!include_group.isEmpty()){
            extent.setSystemInfo("Groups",include_group.toString());
        }
    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getTestClass().getName()); //create new entry in the report.
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+" got successfully executed and passed."); //update status.
    }

    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getTestClass().getName()); //create new entry in the report.
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,result.getName()+" got failed"); //update status.
        test.log(Status.FAIL,result.getThrowable().getMessage()); //update status.

        try {
            String image_path = new BaseClass().capture_screen(result.getName());
            test.addScreenCaptureFromPath(image_path);
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getTestClass().getName()); //create new entry in the report.
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" got skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        extent.flush(); //will update the all things and close the report.

        //Open the report automatically.
        String extent_report_path = System.getProperty("user.dir")+"/reports/"+reportName;
        File extent_report = new File(extent_report_path);

        try{
            Desktop.getDesktop().browse(extent_report.toURI());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
