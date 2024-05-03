package me.abetayev.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import me.abetayev.qa.utils.ExtentManager;
import me.abetayev.qa.utils.Utilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {

        extentReport = ExtentManager.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {

        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + " started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = null;

        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        extentTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver, result.getTestName()));
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, result.getName() + "  has failed execution");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, result.getName() + " was skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReport.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "\\target\\extent-reports\\extentReport.html";
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



















