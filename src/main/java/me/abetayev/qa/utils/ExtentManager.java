package me.abetayev.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentManager {

    public static ExtentReports generateExtentReport() {

        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\target\\extent-reports\\extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("Portfolio - Test Automation Demo");
        sparkReporter.config().setDocumentTitle("Portfolio - Test Automation Demo");
        sparkReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        Properties prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\me\\abetayev\\qa\\config\\config.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}
